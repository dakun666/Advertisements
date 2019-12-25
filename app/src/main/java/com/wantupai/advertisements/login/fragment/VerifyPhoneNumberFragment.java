package com.wantupai.advertisements.login.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wantupai.advertisements.EventKey;
import com.wantupai.advertisements.R;
import com.wantupai.advertisements.login.LoginConstants;

import cn.nightcoder.fasdroid.base.BaseRxFragment;
import cn.nightcoder.fasdroid.bus.Bus;

import static com.wantupai.advertisements.login.LoginConstants.LOGIN_TYPE_BUNDLE_KEY;
import static com.wantupai.advertisements.login.LoginConstants.PHONE_NUMBER_BUNDLE_KEY;

/**
 * Created by xuedakun on 2019-12-23 14:21
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class VerifyPhoneNumberFragment extends BaseRxFragment {

    public static VerifyPhoneNumberFragment create(@LoginConstants.LoginType int loginType) {
        VerifyPhoneNumberFragment fragment = new VerifyPhoneNumberFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LOGIN_TYPE_BUNDLE_KEY, loginType);
        fragment.setArguments(bundle);
        return fragment;
    }

    private int loginType;

    private EditText phoneNumberEdit;

    public VerifyPhoneNumberFragment() {
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_verify_phone_number;
    }

    @Override
    public void initView() {
        phoneNumberEdit = F(R.id.verify_phone_number_edit);
    }

    @Override
    public void initListener() {
        C(F(R.id.verify_phone_number_fragment_back));
        C(F(R.id.phone_number_verify));
    }

    @Override
    public void initData() {
        loginType = getArguments().getInt(LOGIN_TYPE_BUNDLE_KEY);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verify_phone_number_fragment_back:
                Bus.getInstance().postEmpty(EventKey.VERIFY_PHONE_NUMBER_BACK);
                break;
            case R.id.phone_number_verify:
                //TODO 验证手机号，发送验证码
                if (TextUtils.isEmpty(phoneNumberEdit.getText())) {
                    Toast.makeText(getActivity(), R.string.phone_number_null_hint, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phoneNumberEdit.getText().length() < 11) {
                    Toast.makeText(getActivity(), R.string.phone_number_wrong_hint, Toast.LENGTH_SHORT).show();
                    return;
                }
                Bus.getInstance()
                        .build(EventKey.VERIFY_PHONE_NUMBER_PASS)
                        .put(LOGIN_TYPE_BUNDLE_KEY, loginType)
                        .put(PHONE_NUMBER_BUNDLE_KEY, phoneNumberEdit.getText().toString())
                        .post();
                break;
        }
    }
}
