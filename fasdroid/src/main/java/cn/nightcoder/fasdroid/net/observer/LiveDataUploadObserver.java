package cn.nightcoder.fasdroid.net.observer;

import cn.nightcoder.fasdroid.net.callback.UploadCallback;

/**
 * Created by xuedakun on 2019/12/13 16:49
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public abstract class LiveDataUploadObserver<T> extends LiveDataAPIObserver<T> {

    protected UploadCallback<T> uploadCallback;

    public LiveDataUploadObserver(UploadCallback<T> uploadCallback, Class<T> clazz) {
        super(uploadCallback, clazz);
        this.uploadCallback = uploadCallback;
    }
}
