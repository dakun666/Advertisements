package cn.nightcoder.fasdroid.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cn.nightcoder.fasdroid.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by xuedakun on 2019/12/12 16:59
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public abstract class BaseRxActivity extends RxAppCompatActivity implements IBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(layoutId());
        initView();
        initData();
        initListener();
    }

    @Override
    public void initWindow() {
    }

    /**
     * findViewById 创建View实例
     *
     * @param viewId
     * @param <T>
     */
    protected <T extends View> T F(@IdRes int viewId) {
        return findViewById(viewId);
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
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
