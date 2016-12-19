package net.bjyfkj.caa.util;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by YFKJ-1 on 2016/11/7.
 */
public class TimeUtil {
    /**
     * 修改系统时间
     * 根据时间戳修改
     *
     * @param time
     */
    public static void testDate(String time) {
        try {
            Log.i("time", time + "");
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("setprop persist.sys.timezone GMT\n");
            os.writeBytes("/system/bin/date -s " + time + "\n");
            os.writeBytes("clock -w\n");
            os.writeBytes("exit\n");
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断当前时间是上午还是下午
     */
    public static String initWeclomeText() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY) - 8;
        Log.i("hour", "当前时辰是：" + hour);
        if (hour <= 14) {
            return "0";
        } else if (hour >= 15) {
            return "1";
        }
        return "2";
    }

    /***
     * 判断时间戳是否是今天
     *
     * @param timestamp
     */
    public static boolean isNEWDay(String timestamp) {
        String date = DateUtils.getTodayDateTime();
        Log.i("date", date);
        String time = DateUtils.timesTwo(timestamp);
        Log.i("time", time);
        if (date.equals(time)) {
            return true;
        }
        return false;
    }
};