package cn.exi.service.impl;

import cn.exi.helper.DatabaseHelper;
import cn.exi.model.Customer;
import cn.exi.service.CustomerService;
import cn.exi.utils.PropsUtil;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by HuangHailiang on 2017/4/23.
 */
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = Logger.getLogger(CustomerServiceImpl.class);

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    //1.加载数据库连接所需要的参数
    static {
        Properties jdbcConfig = PropsUtil.loadProps("jdbc.properties");
        DRIVER = jdbcConfig.getProperty("jdbc.driver");
        URL = jdbcConfig.getProperty("jdbc.url");
        USERNAME = jdbcConfig.getProperty("jdbc.username");
        PASSWORD = jdbcConfig.getProperty("jdbc.password");

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            log.error("can not load jdbc driver", e);
        }
    }


    @Override
    public List<Customer> getCustomerList(String keywork) throws Exception {
        //使用JDBC来操作数据库
//        Connection connection = DatabaseHelper.getConnection();
//        try {
//            List<Customer> customerList = new ArrayList<>();
//            String sql = "select * from customer";
//            connection =DatabaseHelper.getConnection();
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                Customer customer = new Customer();
//                customer.setId(rs.getLong("id"));
//                customer.setName(rs.getString("name"));
//                customer.setContact(rs.getString("contract"));
//                customer.setTelephone(rs.getString("telephone"));
//                customer.setEmail(rs.getString("email"));
//                customer.setRemark(rs.getString("remark"));
//                customerList.add(customer);
//            }
//            return customerList;
//        } catch (SQLException e) {
//            log.error("execute sql failure", e);

            String sql = "select * from customer";
            return DatabaseHelper.queryEntityList(Customer.class,sql);

    }

    @Override
    public Customer getCustomerById(long id) throws Exception {
        String sql = "select * from customer where id ="+id;
        return DatabaseHelper.queryEntity(Customer.class,sql);
    }

    @Override
    public boolean createCustomer(Map<String, Object> fieldMap) throws Exception {
        return DatabaseHelper.insertEntity(Customer.class,fieldMap);
    }

    @Override
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) throws Exception {
        return DatabaseHelper.updateEntity(Customer.class,id,fieldMap);
    }

    @Override
    public boolean deleteCustomer(long id) throws Exception {
        return DatabaseHelper.deleteEntity(Customer.class,id);
    }

}
