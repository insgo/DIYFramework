package cn.exi.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by HuangHailiang on 2017/4/23.
 *
 * 字符串工具类
 */
public final class StringUtil {

    /**
     * 判断字符串是否为空
     * @param string
     * @return
     */
    public static boolean isEmpty(String string){
        if (string!=null){
            string = string.trim();
        }
        return StringUtils.isEmpty(string);
    }

    /**
     * 判断字符串是否为非空
     * @param string
     * @return
     */
    public static boolean isNotEmpty(String string){

        return !isEmpty(string);
    }
}
