package cn.nightcoder.fasdroid.okhttp.interceptor;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import cn.nightcoder.fasdroid.okhttp.requestBody.UploadExMultipartBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xuedakun on 2019-12-06 11:27
 * 上传使用Interceptor，用于进度回调
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class UploadInterceptor implements Interceptor {

//    private UCallback callback;

    public UploadInterceptor() {
//        this.callback = callback;
//        if (callback == null) {
//            throw new NullPointerException("this callback must not null.");
//        }
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (originalRequest.body() == null) {
            return chain.proceed(originalRequest);
        }
        Request progressRequest = originalRequest.newBuilder()
                .method(originalRequest.method(),
                        new UploadExMultipartBody((MultipartBody) originalRequest.body()))
                .build();
        return chain.proceed(progressRequest);
    }
}