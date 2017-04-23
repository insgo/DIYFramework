package cn.exi.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by HuangHailiang on 2017/4/23.
 * <p>
 * 反射工具类
 * <p>
 * <p>
 * 使用ClassHelper 类可以获取所川载的类，但无法通过类米实例化对象。因此，而~提供一
 * 个反射工具类，让它封装Java 反射相关的A PI ， 对外提供更好用的工具方法。
 */
public final class ReflectionUtil {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ReflectionUtil.class);


    /**
     * 创建实例
     * @param cls
     * @return
     */
    public  static Object newInstance(Class<?> cls){
        Object instance;
        try {
            instance = cls.newInstance();
        }catch (Exception e){
            log.error("new isntance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     *
     */
    public static Object invokeMethod(Object obj, Method method,Object... args){
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(obj,args);
        }catch (Exception e){
            log.error("invoke method failure",e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     */
    public static void setField(Object obj, Field field,Object value){
        try {
            field.setAccessible(true);
            field.set(obj,value);

        }catch (Exception e){
            log.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }

}
