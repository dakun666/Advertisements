package com.wantupai.advertisements.login;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.wantupai.advertisements.EventKey;
import com.wantupai.advertisements.R;
import com.wantupai.advertisements.login.fragment.LoginFragment;
import com.wantupai.advertisements.login.fragment.VerifyPhoneNumberFragment;
import com.wantupai.advertisements.login.fragment.VerifyCodeFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.nightcoder.fasdroid.base.BaseActivity;
import cn.nightcoder.fasdroid.bus.Bus;
import cn.nightcoder.fasdroid.bus.BusMessage;

import static com.wantupai.advertisements.login.LoginConstants.LOGIN_TYPE_BUNDLE_KEY;
import static com.wantupai.advertisements.login.LoginConstants.PHONE_NUMBER_BUNDLE_KEY;

/**
 * Created by xuedakun on 2019-12-23 14:20
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class LoginActivity extends BaseActivity {

    private LoginFragment loginFragment;

    private VerifyPhoneNumberFragment verifyPhoneNumberFragment;

    private VerifyCodeFragment verifyCodeFragment;

    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.login_root_view, loginFragment).commit();
    }

    @Override
    public void initListener() {
        Bus.getInstance().register(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Bus.getInstance().unregister(this);
    }

    @Override
    public void onBackPressed() {
        if (verifyCodeFragment != null && verifyCodeFragment.isAdded() && !verifyCodeFragment.isHidden()) {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.login_fragment_enter, R.anim.login_fragment_exit).remove(verifyCodeFragment).commit();
        } else if (verifyPhoneNumberFragment != null && verifyPhoneNumberFragment.isAdded() && !verifyPhoneNumberFragment.isHidden()) {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.login_fragment_enter, R.anim.login_fragment_exit).remove(verifyPhoneNumberFragment).commit();
        } else {
            //TODO 双击返回回到首页
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BusMessage message) {
        switch (message.getWhat()) {
            case EventKey.BUSINESS_TYPE_LOGIN:
                verifyPhoneNumberFragment = VerifyPhoneNumberFragment.create(LoginConstants.BUSINESS_LOGIN_TYPE);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.login_fragment_enter, R.anim.login_fragment_exit).add(R.id.login_root_view, verifyPhoneNumberFragment).commit();
                break;
            case EventKey.USER_TYPE_LOGIN:
                verifyPhoneNumberFragment = VerifyPhoneNumberFragment.create(LoginConstants.USER_LOGIN_TYPE);
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.login_fragment_enter, R.anim.login_fragment_exit).add(R.id.login_root_view, verifyPhoneNumberFragment).commit();
                break;
            case EventKey.VERIFY_PHONE_NUMBER_PASS:
                int loginType = message.get(LOGIN_TYPE_BUNDLE_KEY, LoginConstants.USER_LOGIN_TYPE);
                String phoneNumber = message.get(PHONE_NUMBER_BUNDLE_KEY, null);
                verifyCodeFragment = VerifyCodeFragment.create(loginType, phoneNumber);
                hideSoftInput();
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.login_fragment_enter, R.anim.login_fragment_exit).add(R.id.login_root_view, verifyCodeFragment).commit();
                break;
            case EventKey.VERIFY_PHONE_NUMBER_BACK:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.login_fragment_enter, R.anim.login_fragment_exit).remove(verifyPhoneNumberFragment).commit();
                break;
            case EventKey.VERIFY_CODE_BACK:
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.login_fragment_enter, R.anim.login_fragment_exit).remove(verifyCodeFragment).commit();
                break;
        }
    }
}
