package cn.nightcoder.fasdroid.net.request.api;

import androidx.lifecycle.LifecycleOwner;

import java.util.HashMap;

import cn.nightcoder.fasdroid.net.HttpUtils;
import cn.nightcoder.fasdroid.net.callback.RequestCallback;
import cn.nightcoder.fasdroid.net.observer.LiveDataAPIObserver;
import cn.nightcoder.fasdroid.net.request.service.ILiveDataService;
import cn.nightcoder.fasdroid.okhttp.callAdapter.livedata.LiveDataCallAdapterFactory;
import cn.nightcoder.fasdroid.okhttp.converter.string.StringConverterFactory;

/**
 * Created by xuedakun on 2019-12-03 13:47
 * 请求执行
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public abstract class LiveDataAPIAPIRequest extends AbsAPIRequest<ILiveDataService> {

    public LiveDataAPIAPIRequest() {
        super(ILiveDataService.class, new LiveDataCallAdapterFactory(), new StringConverterFactory());
    }

    /**
     * Get请求
     *
     * @param lifecycleOwner
     * @param url
     * @param hashMap
     * @param requestCallback
     * @param <T>
     */
    public <T> void doGet(LifecycleOwner lifecycleOwner, String url, HashMap<String, Object> hashMap, Class<T> clazz, RequestCallback<T> requestCallback) {
        service.doGet(url.concat(HttpUtils.getGetRequestParamsStr(hashMap))).observe(lifecycleOwner, observer(requestCallback, clazz));
    }

    /**
     * post请求
     *
     * @param lifecycleOwner
     * @param url
     * @param hashMap
     * @param requestCallback
     * @param <T>
     */
    public <T> void doPost(LifecycleOwner lifecycleOwner, String url, HashMap<String, Object> hashMap, Class<T> clazz, RequestCallback<T> requestCallback) {
        service.doPost(url, hashMap).observe(lifecycleOwner, observer(requestCallback, clazz));
    }

    protected abstract LiveDataAPIObserver<?> observer(RequestCallback requestCallback, Class clazz);
}
