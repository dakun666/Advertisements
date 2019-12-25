package cn.nightcoder.fasdroid.net;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuedakun on 2019/12/13 17:04
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public final class HttpUtils {

    /**
     * @param params 参数map
     * @return get请求所需参数字符串
     */
    public static final String getGetRequestParamsStr(HashMap<String, Object> params) {
        String str = "";
        if (params != null && !params.isEmpty()) {
            for (Map.Entry entry : params.entrySet()) {
                if (TextUtils.isEmpty(str)) {
                    str = str.concat("?");
                } else {
                    str = str.concat("&");
                }
                str = str.concat(entry.getKey() + "=" + entry.getValue());
            }
        }
        return str;
    }

    private HttpUtils() {
        throw new UnsupportedOperationException(
                "Should not create instance of Util class. Please use as static..");
    }
}
