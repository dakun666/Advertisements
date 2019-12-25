package cn.nightcoder.fasdroid.net.request;


import cn.nightcoder.fasdroid.net.CacheConfig;
import cn.nightcoder.fasdroid.okhttp.interceptor.HeaderInterceptor;

/**
 * Created by xuedakun on 2019-12-06 09:17
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public interface IRequest {

    /**
     * @return 自定义baseUrl
     */
    String baseUrl();

    /**
     * @return 自定义头信息
     */
    HeaderInterceptor headers();

    /**
     * @return 连接超时时间
     */
    long connectionTimeout();

    /**
     * @return 读取超时时间
     */
    long readTimeout();

    /**
     * @return 写入超时时间
     */
    long writeTimeout();

    /**
     * @return log开关
     */
    boolean logEnable();

    /**
     * @return 缓存配置
     */
    CacheConfig getCacheConfig();
}
