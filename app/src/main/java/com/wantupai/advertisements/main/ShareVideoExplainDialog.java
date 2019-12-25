package com.wantupai.advertisements.main;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wantupai.advertisements.R;

import cn.nightcoder.fasdroid.base.BaseDialog;

/**
 * Created by xuedakun on 2019-12-24 18:36
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class ShareVideoExplainDialog extends BaseDialog {

    private TextView content;

    @Override
    public int layoutId() {
        return R.layout.dialog_share_video_explain;
    }

    @Override
    public void initView() {
        content = F(R.id.share_video_explain_dialog_content);
    }

    @Override
    public void initWindow() {
        Window window = getDialog().getWindow();
        window.setWindowAnimations(R.style.BottomDialogAnimStyle);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
    }

    @Override
    public void initListener() {
        C(F(R.id.share_video_explain_close));
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_video_explain_close:
                dismiss();
                break;
        }
    }

    public void setCommission(String commission) {
        String contentStr = getString(R.string.video_share_explain_dialog_content, commission);

        String copyStr = getString(R.string.copy);
        String pasteStr = getString(R.string.copy);
        String hours24 = getString(R.string.hours_24);
        SpannableStringBuilder ssb = new SpannableStringBuilder(contentStr);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#FA2121")), contentStr.indexOf(commission), contentStr.indexOf(commission) + commission.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#FA2121")), contentStr.indexOf(commission), copyStr.indexOf(commission) + copyStr.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#FA2121")), contentStr.indexOf(commission), pasteStr.indexOf(commission) + pasteStr.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#FA2121")), contentStr.indexOf(commission), hours24.indexOf(commission) + hours24.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        content.setText(ssb);
    }

    @Override
    protected void initStyle() {
        setStyle(STYLE_NO_FRAME, 0);
    }
}
