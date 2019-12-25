package com.wantupai.advertisements.userCenter.fragment;

import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.wantupai.advertisements.R;

import cn.nightcoder.fasdroid.base.BaseRxFragment;

/**
 * Created by xuedakun on 2019-12-25 17:24
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class UserCenterFragment extends BaseRxFragment {

    private TabLayout sharedListTab;

    private ViewPager2 sharedListPager;

    @Override
    public int layoutId() {
        return R.layout.fragment_user_center;
    }

    @Override
    public void initView() {
        sharedListTab = F(R.id.user_center_shared_list_tab);
        sharedListPager = F(R.id.user_center_shared_list_pager);
    }

    @Override
    public void initListener() {
//        sharedListTab.setupWithViewPager();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
