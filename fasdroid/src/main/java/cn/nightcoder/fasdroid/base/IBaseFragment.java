package cn.nightcoder.fasdroid.base;

import android.view.View;

/**
 * Created by xuedakun on 2019-12-16 09:16
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public interface IBaseFragment extends View.OnClickListener {

    int layoutId();

    void initView();

    void initWindow();

    void initListener();

    void initData();
}
