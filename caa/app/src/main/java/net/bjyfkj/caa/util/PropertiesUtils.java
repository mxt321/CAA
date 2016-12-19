package net.bjyfkj.caa.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by q7728 on 2016/5/13 0013.
 */
public class PropertiesUtils {
    private static Properties properties;

    static {
        properties = new Properties();
        InputStream ins = PropertiesUtils.class.getClassLoader().getResourceAsStream(
                "assets/ipconfig.properties");  // 类名.class.getClassLoader().getResourceAsStream(
        // .properties文件)
        try {
            properties.load(ins);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getpath(String date) {
        return properties.getProperty("rootPath") + properties.getProperty(date);
    }
}
