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
public class PropsUtil {

    private static final Logger log = LoggerFactory.getLogger(PropsUtil.class);

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
}
