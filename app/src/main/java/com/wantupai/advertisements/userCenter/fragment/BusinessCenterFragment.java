package com.wantupai.advertisements.userCenter.fragment;

import android.os.Bundle;
import android.view.View;

import com.wantupai.advertisements.R;

import cn.nightcoder.fasdroid.base.BaseRxFragment;

/**
 * Created by xuedakun on 2019-12-25 17:05
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class BusinessCenterFragment extends BaseRxFragment {

    public static BusinessCenterFragment create(long userId) {
        BusinessCenterFragment businessCenterFragment = new BusinessCenterFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("userId", userId);
        businessCenterFragment.setArguments(bundle);
        return businessCenterFragment;
    }

    private long userId;

    public BusinessCenterFragment() {
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_business_center;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        C(F(R.id.business_center_my_video));
        C(F(R.id.business_center_my_purse));
        C(F(R.id.business_center_settings));
    }

    @Override
    public void initData() {
        userId = getArguments().getLong("userId");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.business_center_my_video:
                //TODO 商家个人中心我的视频 入口
                break;
            case R.id.business_center_my_purse:
                //TODO 商家个人中心我的钱包 入口

                break;
            case R.id.business_center_settings:
                //TODO 商家个人中心钱包 入口

                break;
        }
    }
}
