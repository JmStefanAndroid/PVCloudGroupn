package pv.com.pvcloudgo.utils;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bestleiflee@outlook.com on 12/18/2014.
 */
public class ActivityManager {

    private static ActivityManager instance = new ActivityManager();
    private List<Activity> createdActivities = new ArrayList<>();

    private ActivityManager() {

    }

    public static ActivityManager getInstance() {
        return instance;
    }

    public synchronized void addActivity(Activity activity) {
        if (activity != null) {
            createdActivities.add(activity);
        }
    }

    public synchronized void remove(Activity activity) {
        if (activity != null) {
            createdActivities.remove(activity);
            Log.e("ActivityMac", "removeed:" + activity.getClass().getSimpleName());
        }
    }

    public synchronized void remove(String activity) {
        for (Activity a : createdActivities) {
            if (!a.isFinishing() && a.getClass().getSimpleName().equals(activity)) {
                a.finish();
            }
        }
    }

    public synchronized int getSize() {

        return createdActivities.size();
    }

    public synchronized boolean isActivityRunning(Class<?> c) {
        for (Activity ac : createdActivities) {
            if (ac.getClass().getSimpleName().equals(c.getSimpleName()))
                return true;
        }
        return false;
    }

    public synchronized Class<?> getCurrentStackTop() {
        if (createdActivities.size() == 0)
            return null;
        return createdActivities.get(createdActivities.size() - 1).getClass();
    }

    public synchronized void finishAll() {
        for (Activity a : createdActivities) {
            if (!a.isFinishing()) {
                a.finish();
            }
        }
        createdActivities.clear();
    }

    public synchronized Activity getCurrentActivity() {
        return createdActivities.get(createdActivities.size() - 1);
    }

    public synchronized void finsihActivity(String activityName) {
        int size = createdActivities.size();
        for (int i = 0; i < size; i++) {
            if (createdActivities.get(i).getClass().getSimpleName().equals(activityName)) {
                createdActivities.get(i).finish();
                break;
            }
        }
    }

    /**
     * 关闭指定Activity 以及其之后入栈的所有的Activity
     * @param activityName
     */
    public synchronized void clearTopActivity(String activityName) {
        int size = createdActivities.size();
        boolean onTop=false;
        for (int i = 0; i < size; i++) {
            if (createdActivities.get(i).getClass().getSimpleName().equals(activityName)) {
                onTop=true;
                if(onTop)
                createdActivities.get(i).finish();
            }
        }
    }

}
