package cn.exi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by HuangHailiang on 2017/4/23.
 *
 * $f,，.，操作工具类
 */
public final class StreamUtil {
    private static final Logger log = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 从输入流中获取字符串
     */
    public static String getString(InputStream is){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
        }catch (Exception e){
            log.error("get string failure ",e);
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
