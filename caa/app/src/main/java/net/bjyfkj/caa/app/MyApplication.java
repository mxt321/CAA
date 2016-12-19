package net.bjyfkj.caa.app;

import android.app.Application;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by YFKJ-1 on 2016/10/28.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        JPushInterface.init(this);
        JPushInterface.setDebugMode(true);

    }
}
