package cn.exi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by HuangHailiang on 2017/4/23.
 * <p>
 * 编码与解码操作
 */
public final class CodecUtil {
    private static final Logger log = LoggerFactory.getLogger(CodecUtil.class);


    /**
     * 将URL编码
     */
    public static String encodeURL(String source) {
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (Exception e) {
            log.error("encode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * j将URL解密
     */
    public static String decodeURL(String source) {
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");

        }catch (Exception e){
            log.error("decode URL failure",e);
            throw new RuntimeException(e);
        }

        return target;
    }

}
