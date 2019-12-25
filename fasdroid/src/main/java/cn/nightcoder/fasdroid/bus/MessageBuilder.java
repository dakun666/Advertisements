package cn.nightcoder.fasdroid.bus;

/**
 * Created by xuedakun on 2019-12-03 12:05
 * 创建用以发送的BusMessage
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class MessageBuilder {

    private BusMessage busMessage;


    public MessageBuilder(int what) {
        busMessage = new BusMessage(what);
    }

    public MessageBuilder put(String key, Object value) {
        busMessage.put(key, value);
        return this;
    }

    public void post() {
        Bus.getInstance().post(busMessage);
    }
}
