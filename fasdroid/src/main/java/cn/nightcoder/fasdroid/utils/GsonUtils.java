package cn.nightcoder.fasdroid.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xuedakun on 2019-12-03 13:59
 * 有关Gson使用和Json解析的工具类
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class GsonUtils {

    private static Gson gsonInstance;

    public static Gson gsonInstance() {
        synchronized (GsonUtils.class) {
            if(gsonInstance == null) {
                gsonInstance = new Gson();
            }
            return gsonInstance;
        }
    }

    /**
     * 解析json
     *
     * @param jsonStr
     * @param <T>
     * @return
     */
    public static <T> T json2Object(String jsonStr, TypeToken<T> typeToken) {
        return gsonInstance().fromJson(jsonStr, typeToken.getType());
    }

    /**
     * 解析json
     *
     * @param jsonStr
     * @param <T>
     * @return
     */
    public static <T> T json2Object(String jsonStr, Class<T> clazz) {
        return gsonInstance().fromJson(jsonStr, clazz);
    }

    /**
     * 转json
     *
     * @param o
     * @return
     */
    public static String object2Json(Object o) {
        return gsonInstance().toJson(o);
    }
}
