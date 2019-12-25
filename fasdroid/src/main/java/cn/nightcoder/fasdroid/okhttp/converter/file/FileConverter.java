package cn.nightcoder.fasdroid.okhttp.converter.file;

import android.os.Environment;

import java.io.File;
import java.io.IOException;

import cn.nightcoder.fasdroid.utils.FileUtils;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by xuedakun on 2019-12-05 13:48
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class FileConverter implements Converter<ResponseBody, File> {

    private File file;

    public FileConverter(File file) {
        this.file = file;
    }

    @Override
    public File convert(ResponseBody value) throws IOException {
        FileUtils.whiteBytes2File(file, value.bytes());
        return file;
    }
}