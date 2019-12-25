package cn.nightcoder.fasdroid.net.request.upload;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.concurrent.TimeUnit;

import cn.nightcoder.fasdroid.Fasdroid;
import cn.nightcoder.fasdroid.net.request.IRequest;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Created by xuedakun on 2019/12/13 17:35
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public abstract class AbsUploadRequest<T> implements IRequest {

    private Retrofit.Builder builder = new Retrofit.Builder();

    protected T service;

    public AbsUploadRequest(Class<T> serviceClass, CallAdapter.Factory factory) {
        builder.baseUrl(baseUrl());
        if (factory != null) {
            builder.addCallAdapterFactory(factory);
        }
        builder.client(getUploadOkHttpClient());
        service = builder.build().create(serviceClass);
    }

    @Override
    public long connectionTimeout() {
        return Integer.MAX_VALUE;
    }

    @Override
    public long readTimeout() {
        return Integer.MAX_VALUE;
    }

    @Override
    public long writeTimeout() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean logEnable() {
        return Fasdroid.sDebug;
    }

    private OkHttpClient getUploadOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(connectionTimeout(), TimeUnit.MILLISECONDS);
        builder.readTimeout(readTimeout(), TimeUnit.MILLISECONDS);
        builder.writeTimeout(writeTimeout(), TimeUnit.MILLISECONDS);
        if (logEnable()) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(@NotNull String s) {
                    Timber.tag(getClass().getSimpleName()).d(s);
                }
            });
            loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        if (getCacheConfig() != null && getCacheConfig().isCacheEnable() && getCacheConfig().getCache() != null) {
            CacheInterceptor cacheInterceptor = new CacheInterceptor(getCacheConfig().getCache());
            builder.addNetworkInterceptor(cacheInterceptor);
            builder.addInterceptor(cacheInterceptor);
            builder.cache(getCacheConfig().getCache());
        }
        if (headers() != null) {
            builder.addInterceptor(headers());
        }
        return builder.build();
    }

    /**
     * 生成上传用的MultipartBody.Part
     *
     * @param key
     * @param file
     * @return
     */
    protected final MultipartBody.Part getUploadBodyPart(String key, File file) {
        RequestBody requestFile =
                RequestBody.create(file, MediaType.parse("multipart/form-data"));
        MultipartBody.Part body =
                MultipartBody.Part.createFormData(key, file.getName(), requestFile);
        return body;
    }
}
