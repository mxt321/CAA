package net.bjyfkj.caa.util;

import android.content.Context;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by YFKJ-1 on 2016/10/29.
 */
public class JPushUtil {


    /***
     * 设置别名
     *
     * @param content
     * @param username
     * @return
     */
    public static void setAlias(Context content, final String username) {
        JPushInterface.setAlias(content, username,
                new TagAliasCallback() {
                    @Override
                    public void gotResult(int responseCode,
                                          String alias, Set<String> tags) {
                        if (responseCode == 0) {
                            System.out.println("jpush alias@@@@@别名设置成功  - - " + username);
                        }
                    }
                });
    }
}