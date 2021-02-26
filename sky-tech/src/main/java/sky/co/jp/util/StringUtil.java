package sky.co.jp.util;

public class StringUtil {

    private StringUtil(){};
    public static Integer String2Integer(String str) {
        return str == null? null:Integer.valueOf(str);
    }public static Long String2Long(String str) {
        return str == null? null:Long.valueOf(str);
    }
    public static boolean hasLength(String str){
        return str !=null && !"".equals(str);
    }


}
