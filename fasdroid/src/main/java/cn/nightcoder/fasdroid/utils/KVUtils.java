package cn.nightcoder.fasdroid.utils;

import android.os.Parcelable;

import com.tencent.mmkv.MMKV;

import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Created by xuedakun on 2019-12-09 17:58
 * 利用key-value模型存储序列化对象的工具类,用于替换sharedPreference
 * 基于MMKV https://github.com/Tencent/MMKV
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public class KVUtils {

    private static LinkedHashMap<String, KVAttach> KVAttachMap = new LinkedHashMap<>();

    private static KVAttach defaultKVAttach;

    private KVUtils() { }

    public static KVAttach getDefaultKVAttach() {
        if(defaultKVAttach == null) {
            defaultKVAttach = new KVAttach();
        }
        return defaultKVAttach;
    }
    public static KVAttach getKVAttach(String mmapId) {
        KVAttach kvAttach = KVAttachMap.get(mmapId);
        if(kvAttach == null) {
            kvAttach = new KVAttach(mmapId);
            KVAttachMap.put("mmapId", kvAttach);
        }
        return kvAttach;
    }

    public static class KVAttach {

        private MMKV mmkv;

        public KVAttach() {
            mmkv = MMKV.defaultMMKV();
            mmkv.encode("bool", true);
            mmkv.encode("int", Integer.MIN_VALUE);
        }

        public KVAttach(String mmapId) {
            mmkv = MMKV.mmkvWithID(mmapId, MMKV.MULTI_PROCESS_MODE);
        }

        public KVAttach put(String key, boolean value) {
            mmkv.encode(key, value);
            return this;
        }

        public KVAttach put(String key, int value) {
            mmkv.encode(key, value);
            return this;
        }

        public KVAttach put(String key, long value) {
            mmkv.encode(key, value);
            return this;
        }

        public KVAttach put(String key, float value) {
            mmkv.encode(key, value);
            return this;
        }

        public KVAttach put(String key, double value) {
            mmkv.encode(key, value);
            return this;
        }

        public KVAttach put(String key, String value) {
            mmkv.encode(key, value);
            return this;
        }

        public KVAttach put(String key, byte[] value) {
            mmkv.encode(key, value);
            return this;
        }

        public KVAttach put(String key, Set<String> value) {
            mmkv.encode(key, value);
            return this;
        }

        public KVAttach put(String key, Parcelable parcelable) {
            mmkv.encode(key, parcelable);
            return this;
        }

        public boolean get(String key, boolean defaultValue) {
            return mmkv.decodeBool(key, defaultValue);
        }

        public int get(String key, int defaultValue) {
            return mmkv.decodeInt(key, defaultValue);
        }

        public long get(String key, long defaultValue) {
            return mmkv.decodeLong(key, defaultValue);
        }

        public float get(String key, float defaultValue) {
            return mmkv.decodeFloat(key, defaultValue);
        }

        public double get(String key, double defaultValue) {
            return mmkv.decodeDouble(key, defaultValue);
        }

        public String get(String key, String defaultValue) {
            return mmkv.decodeString(key, defaultValue);
        }

        public byte[] get(String key, byte[] defaultValue) {
            return mmkv.decodeBytes(key, defaultValue);
        }

        public Set<String> get(String key, Set<String> defaultValue) {
            return mmkv.decodeStringSet(key, defaultValue);
        }

        public <T extends Parcelable> T get(String key, Class<T> clazz) {
            return mmkv.decodeParcelable(key, clazz);
        }

        public KVAttach remove(String key) {
            mmkv.removeValueForKey(key);
            return this;
        }

        public boolean contains(String key) {
            return mmkv.contains(key);
        }

        public void clear() {
            mmkv.clear();
        }

        public void clearMemoryCache() {
            mmkv.clearMemoryCache();
        }
    }
}
