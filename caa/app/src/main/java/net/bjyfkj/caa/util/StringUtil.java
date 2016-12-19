package net.bjyfkj.caa.util;

/**
 * Created by YFKJ-1 on 2016/11/14.
 */
public class StringUtil {


    /***
     * 把图片字符串转成数组
     *
     * @return
     */
    public static String[] StringSplit(String str) {
        String[] strs = str.split(",");
        for (int i = 0, len = strs.length; i < len; i++) {
            System.out.println(strs[i].toString());
            strs[i] = strs[i].substring(strs[i].indexOf("\"") + 1, strs[i].indexOf("g") + 1);
            strs[i] = strs[i].replace("\\", "");
            System.out.println(strs[i].toString());
        }
        return strs;
    }
}
