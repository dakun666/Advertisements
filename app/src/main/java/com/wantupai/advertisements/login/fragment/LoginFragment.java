package com.wantupai.advertisements.login.fragment;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.wantupai.advertisements.EventKey;
import com.wantupai.advertisements.R;
import com.wantupai.advertisements.login.OtherTypeLoginDialog;
import com.wantupai.advertisements.login.view.span.ClickableColorSpan;

import cn.nightcoder.fasdroid.base.BaseRxFragment;
import cn.nightcoder.fasdroid.bus.Bus;

/**
 * Created by xuedakun on 2019-12-23 14:20
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class LoginFragment extends BaseRxFragment {

    private TextView loginDisclaimer;

    private OtherTypeLoginDialog otherTypeLoginDialog;

    @Override
    public int layoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView() {
        loginDisclaimer = F(R.id.login_disclaimer);
    }

    @Override
    public void initListener() {
        C(F(R.id.business_type_login));
        C(F(R.id.user_type_login));
        C(F(R.id.other_type_login));
    }

    @Override
    public void initData() {
        String disclaimer = getString(R.string.login_disclaimer_hint);
        String termsOfUser = getString(R.string.terms_of_use);
        String privacyPolicy = getString(R.string.privacy_policy);

        SpannableString spannableString = new SpannableString(disclaimer);
        ClickableColorSpan termsOfUserColorSpan = new ClickableColorSpan(Color.parseColor("#B1C7D4")) {
            @Override
            public void onClick(@NonNull View widget) {
                //TODO 使用条款
                Toast.makeText(getActivity(), "使用条款", Toast.LENGTH_SHORT).show();
            }
        };
        spannableString.setSpan(termsOfUserColorSpan, disclaimer.indexOf(termsOfUser), disclaimer.indexOf(termsOfUser) + termsOfUser.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ClickableColorSpan privacyPolicyColorSpan = new ClickableColorSpan(Color.parseColor("#B1C7D4")) {
            @Override
            public void onClick(@NonNull View widget) {
                //TODO 隐私协定
                Toast.makeText(getActivity(), "隐私协定", Toast.LENGTH_SHORT).show();
            }
        };
        spannableString.setSpan(privacyPolicyColorSpan, disclaimer.indexOf(privacyPolicy), disclaimer.indexOf(privacyPolicy) + privacyPolicy.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        loginDisclaimer.setText(spannableString);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.business_type_login:
                Bus.getInstance().postEmpty(EventKey.BUSINESS_TYPE_LOGIN);
                break;
            case R.id.user_type_login:
                Bus.getInstance().postEmpty(EventKey.USER_TYPE_LOGIN);
                break;
            case R.id.other_type_login:
                if (otherTypeLoginDialog == null) {
                    otherTypeLoginDialog = new OtherTypeLoginDialog();
                }

                otherTypeLoginDialog.show(getChildFragmentManager(), "openTypeLoginDialog");
                break;
        }
    }
}
