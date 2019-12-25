package cn.nightcoder.fasdroid.net.request.api;


import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import cn.nightcoder.fasdroid.Fasdroid;
import cn.nightcoder.fasdroid.net.request.IRequest;
import okhttp3.OkHttpClient;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Created by xuedakun on 2019-12-06 09:17
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public abstract class AbsAPIRequest<T> implements IRequest {

    protected Retrofit.Builder builder = new Retrofit.Builder();

    protected T service;

    public AbsAPIRequest(Class<T> serviceClass, CallAdapter.Factory callAdapterFactory, Converter.Factory convertFactory) {
        builder.baseUrl(baseUrl());
        builder.client(getHttpClient());
        if (callAdapterFactory != null) {
            builder.addCallAdapterFactory(callAdapterFactory);
        }

        if (convertFactory != null) {
            builder.addConverterFactory(convertFactory);
        }
        service = builder.build().create(serviceClass);
    }

    @Override
    public long connectionTimeout() {
        return 7 * 1000;
    }

    @Override
    public long readTimeout() {
        return 10 * 1000;
    }

    @Override
    public long writeTimeout() {
        return 10 * 1000;
    }

    @Override
    public boolean logEnable() {
        return Fasdroid.sDebug;
    }

    /**
     * @return 创建OKHttpClient
     */
    private OkHttpClient getHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(connectionTimeout(), TimeUnit.MILLISECONDS);
        builder.readTimeout(readTimeout(), TimeUnit.MILLISECONDS);
        if (getCacheConfig() != null && getCacheConfig().isCacheEnable() && getCacheConfig().getCache() != null) {
            CacheInterceptor cacheInterceptor = new CacheInterceptor(getCacheConfig().getCache());
            builder.addNetworkInterceptor(cacheInterceptor);
            builder.addInterceptor(cacheInterceptor);
            builder.cache(getCacheConfig().getCache());
        }
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
        if (headers() != null) {
            builder.addInterceptor(headers());
        }
        return builder.build();
    }
}
