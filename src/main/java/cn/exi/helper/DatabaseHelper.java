package cn.exi.helper;


import cn.exi.utils.CollectionUtil;
import cn.exi.utils.PropsUtil;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by HuangHailiang on 2017/4/23.
 * <p>
 * 数据库操作类，声明为final是为了不允许其他类继承它
 */
public final class DatabaseHelper {
    private static final org.apache.log4j.Logger log = Logger.getLogger(DatabaseHelper.class);

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL;
    //增加数据库连接池
    private static final BasicDataSource DATA_SOURCE;

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    //1.加载数据库连接所需要的参数
    static {
        CONNECTION_THREAD_LOCAL = new ThreadLocal<Connection>();

        Properties jdbcConfig = PropsUtil.loadProps("jdbc.properties");
        DRIVER = jdbcConfig.getProperty("jdbc.driver");
        URL = jdbcConfig.getProperty("jdbc.url");
        USERNAME = jdbcConfig.getProperty("jdbc.username");
        PASSWORD = jdbcConfig.getProperty("jdbc.password");

        /**
         * 通过设置driver 、url 、usemame 、password 来初始化
         BasicDataSource ，并调用其getConnection 方法即可获取数据库连接。
         */
        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(DRIVER);
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setUsername(USERNAME);
        DATA_SOURCE.setPassword(PASSWORD);
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            log.error("can not load jdbc driver", e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    public static Connection getConnection() {
        //使用JDBC来操作数据库
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if (connection == null) {
            try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                connection = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                log.error("get connection failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }
        return connection;
    }

    /**
     * 关闭数据库连接
     *
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("close connection failure", e);
            }
        }
    }

    /**
     * 使用线程来-关闭数据库连接
     */
    public static void closeConnection() {
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("close connection failure", e);
                throw new RuntimeException(e);
            } finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }
    }

    /**
     * 查询实体列表
     *
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return 使用DbUtils 提供的QueryRunner 对象可以面向实体（ Entity）进行查询。
     * 实际上， DbUtils 首先执行SQLig句并返回一个ResultSet， 随后通过反射去创建并初始化实体
     * 对象。由于我们需要返回的是List，因此可以使用BeanListHandler。
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, Connection conn, String sql, Object... params) {
        List<T> entityList;
        try {
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            log.error("query entity list failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
        return entityList;
    }

    /**
     * 查询实体列表(使用线程来隔离每个connection)
     *
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return 使用DbUtils 提供的QueryRunner 对象可以面向实体（ Entity）进行查询。
     * 实际上， DbUtils 首先执行SQLig句并返回一个ResultSet， 随后通过反射去创建并初始化实体
     * 对象。由于我们需要返回的是List，因此可以使用BeanListHandler。
     */
    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList;
        try {
            Connection conn = getConnection();
            entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            log.error("query entity list failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return entityList;
    }

    /**
     * 查询单个实体的信息
     *
     * @param entityClass
     * @param sql
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T queryEntity(Class<T> entityClass, String sql, Object... params) {
        T entity;
        try {
            Connection conn = getConnection();
            entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), params);
        } catch (SQLException e) {
            log.error("query entity failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return entity;
    }

    /**
     * 执行查询语句
     *
     * @param sql
     * @param params
     * @return
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object... params) {
        List<Map<String, Object>> result;
        try {
            Connection conn = getConnection();
            result = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params);
        } catch (Exception e) {
            log.error("execute query failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 执行更新语句
     *
     * @param sql    sql语句
     * @param params 参数
     * @return 返回更新条数
     */
    public static int executeUpdate(String sql, Object... params) {
        int rows = 0;
        try {
            Connection conn = getConnection();
            rows = QUERY_RUNNER.update(conn, sql, params);
        } catch (SQLException e) {
            log.error("execute update failure", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return rows;
    }

    /**
     * 插入实体
     *
     * @param entityClass
     * @param fileMap
     * @param <T>
     * @return
     */
    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fileMap) {
        if (CollectionUtil.isEmpty(fileMap)) {
            log.error("can not insert entity:filedMap is empty");
            return false;
        }
        String sql = "insert into" + getTableName(entityClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName : fileMap.keySet()
                ) {
            columns.append(fieldName).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");
        sql += columns + "values " + values;

        Object[] params = fileMap.values().toArray();
        return executeUpdate(sql, params) == 1;
    }


    /**
     * 更新数据
     *
     * @param entityClass
     * @param filedMap
     * @param <T>
     * @return
     */
    public static <T> boolean updateEntity(Class<T> entityClass, long id, Map<String, Object> filedMap) {
        if (CollectionUtil.isEmpty(filedMap)) {
            log.error("can not update entity:filedMap is empty");
            return false;
        }
        String sql = "update " + getTableName(entityClass) + " set ";
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        for (String fieldName : filedMap.keySet()
                ) {
            columns.append(fieldName).append("=?, ");
        }
        sql += columns.substring(0, columns.lastIndexOf(", ")) + " where id=? ";

        List<Object> paramList = new ArrayList<>();
        paramList.addAll(filedMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();
        return executeUpdate(sql, params) == 1;
    }

    /**
     * 删除实体
     *
     * @param entityClass
     * @param id
     * @param <T>
     * @return
     */

    public static <T> boolean deleteEntity(Class<T> entityClass, long id) {
        String sql = "delete from " + getTableName(entityClass) + " where id = ?";
        return executeUpdate(sql, id) == 1;
    }

    private static <T> String getTableName(Class<T> entityClass) {
        return entityClass.getSimpleName();
    }

    /**
     * 执行SQL文件
     */
    public static void executeSQLFile(String filePath){
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        try {
            String sql;
            while ((sql = bufferedReader.readLine())!=null){
                executeUpdate(sql);
            }
        }catch (Exception e){
            log.error("execute sql file failure",e);
            throw new RuntimeException(e);
        }
    }
}
