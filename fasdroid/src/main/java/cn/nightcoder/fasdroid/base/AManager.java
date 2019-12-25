package cn.nightcoder.fasdroid.base;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by xuedakun on 2019/12/17 15:23
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class AManager {

    private static AManager instance;

    public static AManager getInstance() {
        if (instance == null) {
            instance = new AManager();
        }
        return instance;
    }

    private Stack<Activity> activityStack;

    private AManager() {
        activityStack = new Stack<Activity>();
    }

    /**
     * 添加Activity到栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 从栈中移除Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
    }

    /**
     * 栈中是否为空
     *
     * @return
     */
    public boolean isStackEmpty() {
        return activityStack.empty();
    }

    /**
     * 获取当前Activity
     *
     * @return
     */
    public Activity getCurrentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && activity instanceof Activity) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }
}
