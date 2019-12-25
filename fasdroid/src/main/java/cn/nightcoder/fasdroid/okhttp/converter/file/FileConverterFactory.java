package cn.nightcoder.fasdroid.okhttp.converter.file;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by xuedakun on 2019-12-05 13:45
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class FileConverterFactory extends Converter.Factory {

    public static FileConverterFactory create(File file) {
        return new FileConverterFactory(file);
    }

    private File file;

    public FileConverterFactory(File file) {
        this.file = file;
    }

    @Override
    public Converter<ResponseBody, File> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new FileConverter(file);
    }
}