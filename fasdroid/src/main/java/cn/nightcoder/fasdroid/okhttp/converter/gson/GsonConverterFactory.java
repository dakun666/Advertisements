package cn.nightcoder.fasdroid.okhttp.converter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by xuedakun on 2019-12-03 13:31
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class GsonConverterFactory<T> extends Converter.Factory {

    public static <T> GsonConverterFactory create(Class<T> clazz) {
        return create(new Gson(), clazz);
    }

    public static <T> GsonConverterFactory create(Gson gson, Class<T> clazz) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new GsonConverterFactory(gson, clazz);
    }

    private final Gson gson;

    private Class<T> clazz;

    protected GsonConverterFactory(Gson gson, Class<T> clazz) {
        this.gson = gson;
        this.clazz = clazz;
    }

    @Override
    public Converter<ResponseBody, T> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<T> adapter = gson.getAdapter(TypeToken.get(clazz));
        return new GsonResponseBodyConverter(gson, adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GsonRequestBodyConverter<>(gson, adapter);
    }
}