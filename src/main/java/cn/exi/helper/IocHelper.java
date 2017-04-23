package cn.exi.helper;

import cn.exi.utils.ArrayUtil;
import cn.exi.utils.CollectionUtil;
import cn.exi.utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by HuangHailiang on 2017/4/23.
 * <p>
 * 依赖注入助手类
 */
public final class IocHelper {
    static {
        //获取所有的Bean 类与Bean 实例之间的映射关系（简称Bean Map)
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            //遍历Bean Map
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                //从BeanMap 中获取Bean 类与Bean 实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //获取Bea n 类定义的所有成员交全（简称Bean Fi eld)
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    //遍历Bean Field
                    for (Field beanField : beanFields) {
                        //在Bean Map中过去 Bean Field 是否带有inject注解
                        Class<?> beanFieldClass = beanField.getType();
                        Object beanFieldInstance = beanMap.get(beanFieldClass);
                        if (beanFieldInstance != null) {
                            //／／通过反射初始化BeanField 的位
                            ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                        }

                    }
                }
            }
        }
    }
}
