package cn.nightcoder.fasdroid.bus;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by xuedakun on 2019-12-03 11:53
 * 事件总线模块
 * 基于EventBus
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class Bus {

    private static Bus instance;

    private EventBus eventBusInstance;

    public static Bus getInstance() {
        synchronized (Bus.class) {
            if (instance == null) {
                instance = new Bus();
            }
            return instance;
        }
    }

    private Bus() {
        eventBusInstance = EventBus.builder()
                .build();
    }

    public void register(Object subscriber) {
        eventBusInstance.register(subscriber);
    }

    public void unregister(Object subscriber) {
        eventBusInstance.unregister(subscriber);
    }

    public void postEmpty(int what) {
        post(new BusMessage(what));
    }

    public void post(BusMessage busMessage) {
        eventBusInstance.post(busMessage);
    }

    public MessageBuilder build(int what) {
        return new MessageBuilder(what);
    }
}
