package cn.nightcoder.fasdroid.base;

import android.view.View;

/**
 * Created by xuedakun on 2019/12/12 17:10
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public interface IBaseActivity extends View.OnClickListener {

    int layoutId();

    void initWindow();

    void initView();

    void initListener();

    void initData();
}
