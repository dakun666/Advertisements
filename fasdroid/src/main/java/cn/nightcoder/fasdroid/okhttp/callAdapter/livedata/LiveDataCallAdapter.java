package cn.nightcoder.fasdroid.okhttp.callAdapter.livedata;

import androidx.lifecycle.LiveData;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xuedakun on 2019-12-03 13:05
 * 基于LiveData的CallAdapter，适用于OKHttp
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<?>> {

    private Type responseType;

    public LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData adapt(final Call<R> call) {
        final AtomicBoolean started = new AtomicBoolean(false);
        return new LiveData() {
            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(Call<R> call, Response<R> response) {
                            postValue(response.body());
                        }

                        @Override
                        public void onFailure(Call<R> call, Throwable t) {
                            postValue(t);
                        }
                    });
                }
            }
        };
    }
}
