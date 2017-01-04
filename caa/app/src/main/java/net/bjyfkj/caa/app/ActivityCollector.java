package net.bjyfkj.caa.app;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YFKJ-1 on 2017/1/4.
 */

public class ActivityCollector {
    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }

}
