package com.wantupai.advertisements;

import android.app.Application;

import cn.nightcoder.fasdroid.Fasdroid;

/**
 * Created by xuedakun on 2019-12-23 14:23
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class AdvertisementsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fasdroid.init(this, BuildConfig.DEBUG);
    }
}
