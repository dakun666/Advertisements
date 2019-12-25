package cn.nightcoder.fasdroid.okhttp.interceptor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import cn.nightcoder.fasdroid.net.callback.DownloadCallback;
import cn.nightcoder.fasdroid.okhttp.responseBody.DownloadResponseBody;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by xuedakun on 2019-12-05 15:40
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class DownloadInterceptor implements Interceptor {

    private DownloadCallback downloadCallback;

    public DownloadInterceptor(DownloadCallback downloadCallback) {
        this.downloadCallback = downloadCallback;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder().body(
                new DownloadResponseBody(downloadCallback, originalResponse.body()))
                .build();
    }
}
