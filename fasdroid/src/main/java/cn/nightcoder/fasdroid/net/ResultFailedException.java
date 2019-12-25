package cn.nightcoder.fasdroid.net;

/**
 * Created by xuedakun on 2019-12-04 16:22
 * 请求失败的异常
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class ResultFailedException extends Exception {

    public ResultFailedException() {
    }

    public ResultFailedException(String message) {
        super(message);
    }

    public ResultFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultFailedException(Throwable cause) {
        super(cause);
    }
}
