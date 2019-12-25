package cn.nightcoder.fasdroid.net.observer;

import android.util.Log;

import androidx.lifecycle.Observer;

import cn.nightcoder.fasdroid.net.ResultFailedException;
import cn.nightcoder.fasdroid.net.ResultNullException;
import cn.nightcoder.fasdroid.net.callback.RequestCallback;
import cn.nightcoder.fasdroid.utils.GsonUtils;

import static cn.nightcoder.fasdroid.net.ErrorCode.*;

/**
 * Created by xuedakun on 2019-12-03 13:46
 * 用于接收请求结果的观察者
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public abstract class LiveDataAPIObserver<T> implements Observer<String> {

    private static final String LOG_TAG = "ApiObserver";

    protected RequestCallback<T> requestCallback;

    private Class<T> clazz;

    private T baseResult;

    public LiveDataAPIObserver(RequestCallback<T> requestCallback, Class<T> clazz) {
        this.requestCallback = requestCallback;
        this.clazz = clazz;
    }

    @Override
    public void onChanged(String resultStr) {
        if (resultStr != null) {
            try {
                baseResult = GsonUtils.json2Object(resultStr, clazz);
                if (requestCallback != null) {
                    if (baseResult != null) {
                        if (isSuccess(baseResult)) {
                            requestCallback.onRequestSuccess(baseResult);
                        } else {
                            requestCallback.onRequestFailed(code(baseResult), new ResultFailedException(message(baseResult)));
                        }
                    } else {
                        requestCallback.onRequestFailed(NULL_RESULT_ERROR, new ResultNullException());
                    }
                } else {
                    Log.e(LOG_TAG, "requestCallback null!");
                }
            } catch (Exception e) {
                requestCallback.onRequestFailed(LOCAL_REQUEST_ERROR, e);
            }

        } else {
            requestCallback.onRequestFailed(NULL_RESULT_ERROR, new ResultNullException());
        }
    }

    protected abstract boolean isSuccess(T t);

    protected abstract int code(T t);

    protected abstract String message(T t);
}
