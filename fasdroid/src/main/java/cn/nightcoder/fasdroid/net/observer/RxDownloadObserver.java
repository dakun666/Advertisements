package cn.nightcoder.fasdroid.net.observer;

import java.io.File;
import java.io.IOException;

import cn.nightcoder.fasdroid.net.callback.DownloadCallback;
import cn.nightcoder.fasdroid.utils.FileUtils;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by xuedakun on 2019/12/13 15:37
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class RxDownloadObserver implements Observer<ResponseBody> {

    private File file;

    private DownloadCallback downloadCallback;

    public RxDownloadObserver(File file, DownloadCallback downloadCallback) {
        this.file = file;
        this.downloadCallback = downloadCallback;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            FileUtils.whiteBytes2File(file, responseBody.bytes());
            downloadCallback.onDownloadSuccess(file);
        } catch (IOException e) {
            downloadCallback.onDownloadFailed(e);
        }
    }

    @Override
    public void onError(Throwable e) {
        downloadCallback.onDownloadFailed(e);
    }

    @Override
    public void onComplete() {

    }
}
