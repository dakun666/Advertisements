package cn.nightcoder.fasdroid.net.callback;

/**
 * Created by xuedakun on 2019-12-03 13:49
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public interface RequestCallback<T> {

    void onRequestSuccess(T result);

    void onRequestFailed(int code, Throwable throwable);
}
