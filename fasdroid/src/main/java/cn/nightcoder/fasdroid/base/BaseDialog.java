package cn.nightcoder.fasdroid.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import cn.nightcoder.fasdroid.rxlifecycle.components.support.RxAppCompatDialogFragment;

/**
 * Created by xuedakun on 2019/12/16 11:13
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public abstract class BaseDialog extends RxAppCompatDialogFragment implements IBaseFragment{

    protected View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStyle();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(layoutId(), container, false);
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
    public void onResume() {
        initWindow();
        super.onResume();
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }

    /**
     * findViewById 创建View实例
     * 应在initView()中调用
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

    protected abstract void initStyle();
}
