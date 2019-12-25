package cn.nightcoder.fasdroid.net.request.api;


import java.util.HashMap;

import cn.nightcoder.fasdroid.net.HttpUtils;
import cn.nightcoder.fasdroid.net.callback.RequestCallback;
import cn.nightcoder.fasdroid.net.observer.RxAPIObserver;
import cn.nightcoder.fasdroid.net.request.service.IRxService;
import cn.nightcoder.fasdroid.okhttp.callAdapter.rxjava.RxJava3CallAdapterFactory;
import cn.nightcoder.fasdroid.rxandroid.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by xuedakun on 2019/12/12 15:36
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public abstract class RxAPIAPIRequest extends AbsAPIRequest<IRxService> {

    public RxAPIAPIRequest() {
        super(IRxService.class, RxJava3CallAdapterFactory.createAsync(), null);
    }

    /**
     * Get请求
     *
     * @param url
     * @param hashMap
     * @param requestCallback
     * @param <T>
     */
    public <T> void doGet(String url, HashMap<String, Object> hashMap, Class<T> clazz, RequestCallback<T> requestCallback) {
        service.doGet(url.concat(HttpUtils.getGetRequestParamsStr(hashMap)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer(requestCallback, clazz));
    }

    /**
     * post请求
     *
     * @param url
     * @param hashMap
     * @param requestCallback
     * @param <T>
     */
    public <T> void doPost(String url, HashMap<String, Object> hashMap, Class<T> clazz, RequestCallback<T> requestCallback) {
        service.doPost(url, hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer(requestCallback, clazz));
    }

    protected abstract RxAPIObserver observer(RequestCallback requestCallback, Class clazz);
}
