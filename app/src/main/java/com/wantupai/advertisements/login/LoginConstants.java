package com.wantupai.advertisements.login;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by xuedakun on 2019-12-24 11:52
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class LoginConstants {

    //商家登录标示
    public static final int BUSINESS_LOGIN_TYPE = 1;

    //用户登录标示
    public static final int USER_LOGIN_TYPE = 2;

    @IntDef({BUSINESS_LOGIN_TYPE, USER_LOGIN_TYPE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LoginType {
    }

    public static final String LOGIN_TYPE_BUNDLE_KEY = "loginType";
    public static final String PHONE_NUMBER_BUNDLE_KEY = "phoneNumber";

    private LoginConstants() {
    }
}
