package cn.nightcoder.fasdroid.net.callback;

import java.io.File;

/**
 * Created by xuedakun on 2019/12/13 15:37
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public interface DownloadCallback {

    void onDownloadSuccess(File file);

    void onDownloadFailed(Throwable t);

    void downloadProgress(long totalLength, long contentLength);
}