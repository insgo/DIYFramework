package cn.exi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by HuangHailiang on 2017/4/23.
 *
 * 属性文件工具类
 */
public  final class PropsUtil {

    private static final Logger log = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     * @param fileName 属性文件的名称
     * @return
     */
    public static Properties loadProps(String fileName){
        Properties properties = null;
        InputStream inputStream = null;


        try {
             inputStream  = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new FileNotFoundException(fileName+"file is not found");
            }
            properties = new Properties();
            properties.load(inputStream);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (inputStream !=null){
                try {
                    inputStream.close();
                }catch (IOException e ){
                    log.error("close input stream failuse",e);
                }
            }

        }
        return properties;

    }

    /**
     * 获取字符型属性（默认值为空字符串）
     * @param pros 文件
     * @param key
     * @return
     */
    public static String getString(Properties pros,String key){
        return getString(pros,key,"");
    }

    public static String getString(Properties pros, String key, String defaultValus) {
        String value = defaultValus;
        if (pros.containsKey(key)){
            value = pros.getProperty(key);
        }
        return value;
    }

    /**
     * 获取数值属性值（默认值为0）
     * @param props
     * @param key
     * @return
     */
    public static int getInt(Properties props,String key){
        return getInt(props,key,0);
    }

    private static int getInt(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if (props.containsKey(key)){
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取布尔型属性值（默认值为false）
     * @param props
     * @param key
     * @return
     */
    public static boolean getBoolean(Properties props,String key){
        return getBoolean(props,key,false);
    }

    private static boolean getBoolean(Properties props, String key, boolean defaultValue) {
        boolean value = defaultValue;
        if (props.containsKey(key)){
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }
}
