package com.wantupai.advertisements.login;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.wantupai.advertisements.R;

import cn.nightcoder.fasdroid.base.BaseDialog;

/**
 * Created by xuedakun on 2019-12-24 11:56
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class OtherTypeLoginDialog extends BaseDialog {

    @Override
    public int layoutId() {
        return R.layout.dialog_other_type_login;
    }

    @Override
    public void initView() {

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
        C(F(R.id.other_type_login_dialog_tik_tok));
        C(F(R.id.other_type_login_dialog_cancel));
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.other_type_login_dialog_tik_tok:
                //TODO 抖音登录

                break;
            case R.id.other_type_login_dialog_cancel:
                dismiss();
                break;
        }
    }

    @Override
    protected void initStyle() {
        setStyle(STYLE_NO_FRAME, 0);
    }
}
