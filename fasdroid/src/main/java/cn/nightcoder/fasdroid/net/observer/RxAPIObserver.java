package cn.nightcoder.fasdroid.net.observer;

import android.Manifest;

import androidx.annotation.RequiresPermission;

import cn.nightcoder.fasdroid.net.ResultFailedException;
import cn.nightcoder.fasdroid.net.ResultNullException;
import cn.nightcoder.fasdroid.net.callback.RequestCallback;
import cn.nightcoder.fasdroid.utils.GsonUtils;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.ResponseBody;
import timber.log.Timber;

import static cn.nightcoder.fasdroid.net.ErrorCode.LOCAL_REQUEST_ERROR;
import static cn.nightcoder.fasdroid.net.ErrorCode.NET_REQUEST_ERROR;
import static cn.nightcoder.fasdroid.net.ErrorCode.NULL_RESULT_ERROR;

/**
 * Created by xuedakun on 2019/12/12 16:51
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public abstract class RxAPIObserver<T> implements Observer<ResponseBody> {

    protected RequestCallback<T> requestCallback;

    private Class<T> clazz;

    private T baseResult;

    public RxAPIObserver(RequestCallback<T> requestCallback, Class<T> clazz) {
        this.requestCallback = requestCallback;
        this.clazz = clazz;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResponseBody responseBody) {
        if (requestCallback != null) {
            try {
                if (responseBody != null) {
                    baseResult = GsonUtils.json2Object(responseBody.string(), clazz);
                    if (baseResult != null) {
                        if (isSuccess(baseResult)) {
                            requestCallback.onRequestSuccess(baseResult);
                        } else {
                            requestCallback.onRequestFailed(code(baseResult), new ResultFailedException(message(baseResult)));
                        }
                    } else {
                        requestCallback.onRequestFailed(NULL_RESULT_ERROR, new ResultNullException());
                    }
                }else {
                    requestCallback.onRequestFailed(LOCAL_REQUEST_ERROR, new ResultNullException("responseBody null!"));
                    Timber.d("responseBody null!");
                }
            } catch (Exception e) {
                requestCallback.onRequestFailed(NULL_RESULT_ERROR, e);
                Timber.e(e);
            }
        } else {
            Timber.d("requestCallback null!");
        }
    }

    @Override
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public void onError(Throwable e) {
        requestCallback.onRequestFailed(NET_REQUEST_ERROR, e);
        Timber.e(e);
    }

    @Override
    public void onComplete() {

    }

    protected abstract boolean isSuccess(T t);

    protected abstract int code(T t);

    protected abstract String message(T t);
}
