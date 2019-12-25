package cn.nightcoder.fasdroid.net.observer;

import androidx.lifecycle.Observer;

import java.io.File;
import java.io.IOException;

import cn.nightcoder.fasdroid.net.callback.DownloadCallback;
import cn.nightcoder.fasdroid.utils.FileUtils;
import okhttp3.ResponseBody;

/**
 * Created by xuedakun on 2019-12-05 13:42
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class LiveDataDownloadObserver implements Observer<ResponseBody> {

    private File file;

    private DownloadCallback downloadCallback;

    public LiveDataDownloadObserver(File file, DownloadCallback downloadCallback) {
        this.file = file;
        this.downloadCallback = downloadCallback;
    }

    @Override
    public void onChanged(ResponseBody responseBody) {
        try {
            FileUtils.whiteBytes2File(file, responseBody.bytes());
            downloadCallback.onDownloadSuccess(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
