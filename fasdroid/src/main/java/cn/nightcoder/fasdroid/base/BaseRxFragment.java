package cn.nightcoder.fasdroid.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cn.nightcoder.fasdroid.rxlifecycle.components.support.RxSupportFragment;

/**
 * Created by xuedakun on 2019-12-16 09:20
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public abstract class BaseRxFragment extends RxSupportFragment implements IBaseFragment {

    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(layoutId(), container, false);
        rootView.setClickable(true);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initListener();
    }

    @Override
    public void initWindow() {

    }

    /**
     * findViewById 创建View实例
     * 应在initView()中调用
     *
     * @param viewId
     * @param <T>
     */
    protected <T extends View> T F(@IdRes int viewId) {
        return rootView.findViewById(viewId);
    }

    /**
     * 设置view点击事件
     *
     * @param view
     * @param <E>
     */
    protected <E extends View> void C(@NonNull E view) {
        view.setOnClickListener(this);
    }

    /**
     * 隐藏键盘
     */
    protected void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.hideSoftInputFromWindow(rootView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
