package com.wantupai.advertisements;

import android.content.Intent;
import android.view.View;

import com.wantupai.advertisements.login.LoginActivity;
import com.wantupai.advertisements.main.MainActivity;
import com.wantupai.advertisements.userCenter.UserCenterActivity;

import cn.nightcoder.fasdroid.base.BaseActivity;

public class LauncherActivity extends BaseActivity {

    @Override
    public int layoutId() {
        return R.layout.activity_launcher;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        C(F(R.id.btn_test_login));
        C(F(R.id.btn_test_main));
        C(F(R.id.btn_test_user_center));
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test_login:
                startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
                break;
            case R.id.btn_test_main:
                startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                break;
            case R.id.btn_test_user_center:
                startActivity(new Intent(LauncherActivity.this, UserCenterActivity.class));
                break;
        }
    }
}
