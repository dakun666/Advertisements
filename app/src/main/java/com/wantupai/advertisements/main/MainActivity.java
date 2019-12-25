package com.wantupai.advertisements.main;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.wantupai.advertisements.R;
import com.wantupai.advertisements.main.model.ShareVideoModel;

import cn.nightcoder.fasdroid.base.BaseRxActivity;

/**
 * Created by xuedakun on 2019-12-24 16:33
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class MainActivity extends BaseRxActivity implements ShareVideoAdapter.OnShareVideoItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ImageView userAvatar;

    private SwipeRefreshLayout refreshLayout;

    private RecyclerView shareVideoListView;

    private ShareVideoAdapter shareVideoAdapter;

    private ShareVideoExplainDialog shareVideoExplainDialog;

    @Override
    public int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        userAvatar = F(R.id.main_user_avatar);
        refreshLayout = F(R.id.main_refresh_layout);
        shareVideoListView = F(R.id.main_share_video_list_view);

        refreshLayout.setColorSchemeResources(R.color.default_gradient_color_0,
                R.color.default_gradient_color_1);

        refreshLayout.setProgressBackgroundColorSchemeResource(R.color.default_background_color);
    }

    @Override
    public void initListener() {
        C(userAvatar);
        C(F(R.id.main_fast_production_video));
        C(F(R.id.main_import_video));
        C(F(R.id.main_take_video));
        C(F(R.id.main_analytic_video));
        C(F(R.id.main_video_model));

        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void initData() {
        shareVideoAdapter = new ShareVideoAdapter(this, this);
        shareVideoListView.setAdapter(shareVideoAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_user_avatar:
                //TODO 用户头像跳转个人主页

                break;
            case R.id.main_fast_production_video:
                //TODO 快速制作

                break;
            case R.id.main_import_video:
                //TODO 导入剪辑

                break;
            case R.id.main_take_video:
                //TODO 拍摄视频

                break;
            case R.id.main_analytic_video:
                //TODO 解析视频

                break;
            case R.id.main_video_model:
                //TODO 视频模版

                break;
        }
    }

    @Override
    public void playVideo(ShareVideoModel shareVideoModel) {
        //TODO 首页播放视频
        Toast.makeText(this, "播放" + shareVideoModel.getVideoUrl(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moreOption(ShareVideoModel shareVideoModel) {
        if(shareVideoModel != null) {
            if(shareVideoExplainDialog == null) {
                shareVideoExplainDialog = new ShareVideoExplainDialog();
            }
            shareVideoExplainDialog.setCommission(shareVideoModel.getCommission());
            shareVideoExplainDialog.show(getSupportFragmentManager(), "shareVideoExplainDialog");
        }
    }

    @Override
    public void shareVideo(ShareVideoModel shareVideoModel) {
        //TODO 分享视频

    }

    @Override
    public void onRefresh() {
        //TODO 下拉刷新首页列表
        new Handler().postDelayed(() -> refreshLayout.setRefreshing(false), 1000);
    }
}
