package cn.nightcoder.fasdroid.okhttp.responseBody;

import java.io.IOException;

import cn.nightcoder.fasdroid.net.callback.DownloadCallback;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by xuedakun on 2019-12-05 15:41
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class DownloadResponseBody extends ResponseBody {


    private final ResponseBody responseBody;

    private DownloadCallback downloadCallback;

    private BufferedSource bufferedSource;

    public DownloadResponseBody(DownloadCallback downloadCallback, ResponseBody responseBody) {
        this.downloadCallback = downloadCallback;
        this.responseBody = responseBody;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                long contentLength = responseBody.contentLength();
                // read() returns the number of bytes read, or -1 if this source is exhausted.
//                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                if (bytesRead == -1) { // this source is exhausted
                    totalBytesRead = contentLength;
                } else {
                    totalBytesRead += bytesRead;
                }
                if(downloadCallback != null) {
                    downloadCallback.downloadProgress(totalBytesRead, contentLength);
                }
                return bytesRead;
            }
        };
    }
}