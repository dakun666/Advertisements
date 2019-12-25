package cn.nightcoder.fasdroid.okhttp.interceptor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xuedakun on 2019-12-03 13:26
 * 请求头
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public abstract class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        Headers.Builder headerBuilder = request.headers().newBuilder();
        if (header() != null) {
            for (Map.Entry<String, String> entry : header().entrySet()) {
                headerBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        Request build = requestBuilder.headers(headerBuilder.build()).build();

        return chain.proceed(build);
    }

    public abstract Map<String, String> header();
}
