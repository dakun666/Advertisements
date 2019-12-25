package cn.nightcoder.fasdroid.net.request.download;

import androidx.lifecycle.LifecycleOwner;

import java.io.File;

import cn.nightcoder.fasdroid.net.callback.DownloadCallback;
import cn.nightcoder.fasdroid.net.observer.LiveDataDownloadObserver;
import cn.nightcoder.fasdroid.net.request.service.ILiveDataService;
import cn.nightcoder.fasdroid.okhttp.callAdapter.livedata.LiveDataCallAdapterFactory;

/**
 * Created by xuedakun on 2019-12-05 13:27
 * LiveData实现下载
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public abstract class LiveDataDownloadRequest extends AbsDownloadRequest<ILiveDataService> {

    protected LiveDataDownloadRequest(DownloadCallback downloadCallback) {
        super(ILiveDataService.class, new LiveDataCallAdapterFactory(), downloadCallback);
    }

    /**
     * @param lifecycleOwner
     * @param url            下载地址
     * @param file           存储路径
     */
    public void download(LifecycleOwner lifecycleOwner, String url, File file) {
        service.doDownload(url).observe(lifecycleOwner, new LiveDataDownloadObserver(file, downloadCallback));
    }
}
