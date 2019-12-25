package cn.nightcoder.fasdroid.net.request.download;

import java.io.File;

import cn.nightcoder.fasdroid.net.callback.DownloadCallback;
import cn.nightcoder.fasdroid.net.observer.RxDownloadObserver;
import cn.nightcoder.fasdroid.net.request.service.IRxService;
import cn.nightcoder.fasdroid.okhttp.callAdapter.rxjava.RxJava3CallAdapterFactory;
import cn.nightcoder.fasdroid.rxandroid.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by xuedakun on 2019/12/13 15:36
 * RxJava 实现下载
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public abstract class RxDownloadAPIRequest extends AbsDownloadRequest<IRxService> {

    protected RxDownloadAPIRequest(DownloadCallback downloadCallback) {
        super(IRxService.class, RxJava3CallAdapterFactory.createAsync(), downloadCallback);
    }

    /**
     * @param url  下载地址
     * @param file 存储路径
     */
    public void download(String url, File file) {
        service.doDownload(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxDownloadObserver(file, downloadCallback));
    }
}
