package cn.nightcoder.fasdroid.okhttp.callAdapter.livedata;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by xuedakun on 2019-12-03 13:06
 * 基于LiveData的CallAdapterFactory，适用于OKHttp
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory {

    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != LiveData.class) {
            throw new IllegalStateException("return type must be parameterized");
        }
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class rawObservableType = getRawType(observableType);
        Type responseType;
        if (rawObservableType == Response.class) {
            if (observableType instanceof ParameterizedType) {
                responseType = getParameterUpperBound(0, (ParameterizedType) observableType);
            } else {
                throw new IllegalArgumentException("Response must be parameterized");
            }
        } else {
            responseType = observableType;
        }
        return new LiveDataCallAdapter<>(responseType);
    }
}
