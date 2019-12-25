package com.wantupai.advertisements.login.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wantupai.advertisements.EventKey;
import com.wantupai.advertisements.R;
import com.wantupai.advertisements.login.LoginConstants;
import com.wantupai.advertisements.login.view.VerifyCodeEdit;

import cn.nightcoder.fasdroid.base.BaseRxFragment;
import cn.nightcoder.fasdroid.bus.Bus;

import static com.wantupai.advertisements.login.LoginConstants.LOGIN_TYPE_BUNDLE_KEY;
import static com.wantupai.advertisements.login.LoginConstants.PHONE_NUMBER_BUNDLE_KEY;

/**
 * Created by xuedakun on 2019-12-23 14:22
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class VerifyCodeFragment extends BaseRxFragment implements VerifyCodeEdit.VerifyCodeInputFinishListener {

    public static VerifyCodeFragment create(@LoginConstants.LoginType int loginType, String phoneNumber) {
        VerifyCodeFragment fragment = new VerifyCodeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LOGIN_TYPE_BUNDLE_KEY, loginType);
        bundle.putString(PHONE_NUMBER_BUNDLE_KEY, phoneNumber);
        fragment.setArguments(bundle);
        return fragment;
    }

    private TextView hintText;
    private VerifyCodeEdit verifyCodeEdit;

    private String phoneNumber;

    public VerifyCodeFragment() {
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_verify_code;
    }

    @Override
    public void initView() {
        hintText = F(R.id.verify_code_hint);
        verifyCodeEdit = F(R.id.verify_code_edit);
    }

    @Override
    public void initListener() {
        C(F(R.id.verify_code_fragment_back));
        C(F(R.id.retry_send_verify_code));
        verifyCodeEdit.setInputFinishListener(this);
    }

    @Override
    public void initData() {
        phoneNumber = getArguments().getString(PHONE_NUMBER_BUNDLE_KEY);
        String verifyCodeHint = getString(R.string.verify_code_hint, phoneNumber);
        SpannableStringBuilder ssb = new SpannableStringBuilder(verifyCodeHint);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#FA2121")), verifyCodeHint.indexOf(phoneNumber), verifyCodeHint.indexOf(phoneNumber) + phoneNumber.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        hintText.setText(ssb);

        verifyCodeEdit.requestFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verify_code_fragment_back:
                Bus.getInstance().postEmpty(EventKey.VERIFY_CODE_BACK);
                break;
            case R.id.retry_send_verify_code:
                //TODO 重新发送验证码

                break;
        }
    }

    @Override
    public void onInputFinish(String verifyCode) {
        //TODO 提交验证码
        hideSoftInput();
        Toast.makeText(getActivity(), verifyCode, Toast.LENGTH_SHORT).show();
    }
}
