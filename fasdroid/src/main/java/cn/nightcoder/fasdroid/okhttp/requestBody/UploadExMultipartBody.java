package cn.nightcoder.fasdroid.okhttp.requestBody;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by xuedakun on 2019-12-06 11:12
 * 上传文件所使用RequestBody
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class UploadExMultipartBody extends RequestBody {

    private MultipartBody multipartBody;

//    private UploadProgressListener mProgressListener;
    private long currentLength;

    public UploadExMultipartBody(MultipartBody multipartBody) {
        this.multipartBody = multipartBody;
    }

//    public UploadExMultipartBody(MultipartBody multipartBody, UploadProgressListener progressListener) {
//        this.multipartBody = multipartBody;
//        this.mProgressListener = progressListener;
//    }

    @Override
    public MediaType contentType() {
        return multipartBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return multipartBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        BufferedSink bufferedSink = Okio.buffer(new ForwardingSinkImpl(sink));
        multipartBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    private class ForwardingSinkImpl extends ForwardingSink {

        public ForwardingSinkImpl(@NotNull Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(@NotNull Buffer source, long byteCount) throws IOException {
            currentLength += byteCount;
//            if (mProgressListener != null) {
//                mProgressListener.onProgress(totalLength, currentLength);
//            }
            super.write(source, byteCount);
        }
    }

}
