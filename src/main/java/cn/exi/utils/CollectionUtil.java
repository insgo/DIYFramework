package cn.exi.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by HuangHailiang on 2017/4/23.
 *
 * 集合工具类(此集合分明就是直接调用了Java中的Utils，为什么要重复造轮子？)
 */
public final class CollectionUtil {
    /**
     * 判断Collection是否为空
     * @param collection 集合
     * @return
     */
    public static boolean isEmpty(Collection<?> collection){
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断Collection是否为非空
     * @param collection 集合
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return CollectionUtils.isNotEmpty(collection);
    }


    /**
     * 判断Map是否为空
     * @param map 集合
     * @return
     */
    public static boolean isEmpty(Map<?,?> map){
        return MapUtils.isEmpty(map);
    }

    /**
     * 判断Map是否为空
     * @param map 集合
     * @return
     */
    public static boolean isNotEmpty(Map<?,?> map){
        return MapUtils.isNotEmpty(map);
    }
}
