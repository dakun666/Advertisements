package cn.nightcoder.fasdroid.net;

/**
 * Created by xuedakun on 2019-12-04 16:09
 * 结果为空的异常
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class ResultNullException extends Exception {

    public ResultNullException() {
        super();
    }

    public ResultNullException(String message) {
        super(message);
    }

    public ResultNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultNullException(Throwable cause) {
        super(cause);
    }
}
