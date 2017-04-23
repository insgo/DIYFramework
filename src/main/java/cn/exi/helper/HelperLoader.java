package cn.exi.helper;

import cn.exi.utils.ClassUtil;

/**
 * Created by HuangHailiang on 2017/4/23.
 *
 *
 * 加载相应的Handler
 */
public final class HelperLoader {
    public static void init(){
        Class<?>[] classes = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls:classes){
            ClassUtil.loadClass(cls.getName(),false);
        }
    }
}
