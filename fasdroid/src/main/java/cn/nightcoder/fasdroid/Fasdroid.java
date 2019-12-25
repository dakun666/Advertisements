package cn.nightcoder.fasdroid;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;
import com.tencent.mmkv.MMKV;

import cn.nightcoder.fasdroid.base.AManager;
import cn.nightcoder.fasdroid.utils.NetworkUtils;
import timber.log.Timber;

/**
 * Created by xuedakun on 2019-12-09 18:26
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public final class Fasdroid {


    private static boolean isInit = false;

    public static boolean sDebug = true;

    private static Context sAppContext;

    private Fasdroid() {
    }

    /**
     * 在Application onCreate()中调用
     *
     * @param application
     */
    public static void init(Application application, boolean debug) {
        if (isInit) {

        }
        sAppContext = application.getApplicationContext();
        if (debug) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new FasdroidLogTree());
        }
        sDebug = debug;

        NetworkUtils.init(application.getApplicationContext());

        MMKV.initialize(application.getApplicationContext());
        QMUISwipeBackActivityManager.init(application);
        isInit = true;

        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                AManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                AManager.getInstance().removeActivity(activity);
            }
        });
    }

    /**
     * 获得当前app运行的Application
     */
    public static Context getAppContext() {
        if (sAppContext == null) {
            throw new NullPointerException("Application Context null!");
        }
        return sAppContext;
    }
}
