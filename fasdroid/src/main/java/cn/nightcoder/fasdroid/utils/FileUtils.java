package cn.nightcoder.fasdroid.utils;

import java.io.File;
import java.io.IOException;

import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by xuedakun on 2019-12-05 13:51
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class FileUtils {

    /**
     * 字节写入文件
     *
     * @param file
     * @param bytes
     * @throws IOException
     */
    public static void whiteBytes2File(File file, byte[] bytes) throws IOException {
        boolean isCreate;
        if (!file.exists()) {
            isCreate = file.createNewFile();
        } else {
            isCreate = true;
        }
        if (isCreate) {
            Sink sink = Okio.sink(file);
            BufferedSink bufferedSink = Okio.buffer(sink);
            bufferedSink.write(bytes);
            bufferedSink.flush();
        }
    }
}
