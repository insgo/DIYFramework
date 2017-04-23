package cn.exi.utils;

/**
 * Created by HuangHailiang on 2017/4/23.
 *
 * 操作转型的工具类
 */
public final class CastUtil {

    public static String castString(Object object){
        return CastUtil.castString(object,"");
    }

    /**
     * 转为String类型
     * @param object
     * @param defaultValue
     * @return
     */
    private static String castString(Object object, String defaultValue) {
        return object !=null?String.valueOf(object):defaultValue;
    }


    public static double castDouble(Object object){
        return CastUtil.castDouble(object,0);
    }

    /**
     * 转为double类型
     * @param object
     * @param defaultValue
     * @return
     */
    private static double castDouble(Object object, double defaultValue) {
        double doubleValue = defaultValue;
       if (object!=null){
           String strValue = castString(object);
           if (StringUtil.isNotEmpty(strValue)){
               try{
                   doubleValue=Double.parseDouble(strValue);
               }catch (NumberFormatException e){
                   doubleValue=defaultValue;
               }
           }
       }
       return doubleValue;
    }

    /**
     * 转换为long类型
     * @param object
     * @return
     */
    public static long castLong(Object object){
        return CastUtil.castLong(object,0);
    }

    private static long castLong(Object object, long defaultValue) {
        long longValue = defaultValue;
        if (object!=null){
            String strValue = castString(object);
            if (StringUtil.isEmpty(strValue)){
                try {
                    longValue=Long.parseLong(strValue);
                }catch (NumberFormatException e){
                    longValue=defaultValue;
                }
            }
        }
        return longValue;
    }

    /**
     * 转换为int类型
     * @param object
     * @return
     */
    public static int castInt(Object object){
        return CastUtil.castInt(object,0);
    }

    private static int castInt(Object object, int defaultValue) {
        int intValue = defaultValue;
        if (object!=null){
            String strValue = castString(object);
            if (StringUtil.isEmpty(strValue)){
                try {
                    intValue=Integer.parseInt(strValue);
                }catch (NumberFormatException e){
                    intValue=defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     * 转换为boolean类型
     * @param object
     * @return
     */
    public static boolean castBoolean(Object object){
        return CastUtil.castBoolean(object,false);
    }

    private static boolean castBoolean(Object object, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (object!=null){
           booleanValue = Boolean.parseBoolean(castString(object));
        }
        return booleanValue;
    }
}
