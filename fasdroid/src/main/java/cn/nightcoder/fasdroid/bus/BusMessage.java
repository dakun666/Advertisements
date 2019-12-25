package cn.nightcoder.fasdroid.bus;

import java.util.HashMap;

/**
 * Created by xuedakun on 2019-12-03 12:00
 * 事件总线Message对象。使用key-value存储读取传递的值
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class BusMessage {

    private int what;

    private HashMap messageMap;

    public BusMessage(int what) {
        this.what = what;
    }

    BusMessage put(String key, Object message) {
        if (messageMap == null) {
            messageMap = new HashMap();
        }
        messageMap.put(key, message);
        return this;
    }

    public <T> T get(String key, T defaultValue) {
        if (messageMap != null) {
            try {
                return (T) messageMap.get(key);
            } catch (ClassCastException e) {
                return defaultValue;
            }
        }
        return null;
    }

    public int getWhat() {
        return what;
    }
}
