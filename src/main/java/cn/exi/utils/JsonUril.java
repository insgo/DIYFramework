package cn.exi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by HuangHailiang on 2017/4/23.
 *
 * JSON 工具类
 */
public final class JsonUril {

    private static final Logger log = LoggerFactory.getLogger(JsonUril.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将POJO转为Json
     *
     */
    public static <T> String toJson(T obj){
        String json;
        try {
            json= OBJECT_MAPPER.writeValueAsString(obj);
        }catch (Exception e){
            log.error("convert POJO to JSON failure" ,e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将JSON转为POJO
     */
    public static <T> T fromJson(String json ,Class<T> type){
        T pojo;
        try {
            pojo= OBJECT_MAPPER.readValue(json,type);
        }catch (Exception e){
            log.error("convert JSON to POJO failure",e);
            throw  new RuntimeException(e);
        }
        return pojo;
    }
}
