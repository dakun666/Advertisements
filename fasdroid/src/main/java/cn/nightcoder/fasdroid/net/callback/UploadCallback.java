package cn.nightcoder.fasdroid.net.callback;

/**
 * Created by xuedakun on 2019/12/13 16:48
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public interface UploadCallback<T> extends RequestCallback<T> {

    void uploadProgress(long totalLength, long contentLength);

}