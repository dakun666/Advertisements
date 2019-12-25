package cn.nightcoder.fasdroid.net;

import okhttp3.Cache;

/**
 * Created by xuedakun on 2019/12/12 16:11
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class CacheConfig {

    private boolean cacheEnable;

    private Cache cache;

    public CacheConfig(boolean cacheEnable, Cache cache) {
        this.cacheEnable = cacheEnable;
        this.cache = cache;
    }

    public boolean isCacheEnable() {
        return cacheEnable;
    }

    public Cache getCache() {
        return cache;
    }
}
