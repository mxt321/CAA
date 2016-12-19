package net.bjyfkj.caa.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by q7728 on 2016/5/18 0018.
 */
public class GsonUtils {
    static Gson gson = new Gson();

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

    public static <T> T json2Bean(String result, Class<T> tClass) {
        Gson gson = new Gson();
        T t = gson.fromJson(result, tClass);
        return t;
    }
}
