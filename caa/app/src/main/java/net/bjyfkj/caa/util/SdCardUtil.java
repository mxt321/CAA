package net.bjyfkj.caa.util;

import java.io.File;

/**
 * Created by YFKJ-1 on 2016/11/5.
 */
public class SdCardUtil {

    /**
     * 判断文件是否存在
     *
     * @param path
     * @return
     */
    public static boolean fileIsExists(String path) {
        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        return false;
    }


    /***
     * 根据文件名截取后缀名
     *
     * @param fileName
     * @return
     */

    public static String getFileType(String fileName) {
        String path = fileName.substring(fileName.lastIndexOf("."));
        return path;
    }


}
