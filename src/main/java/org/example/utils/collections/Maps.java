package org.example.utils.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Maps {

    private Maps(){}

    /**
     * 创建一个新的HashMap
     * @return
     * @param <K>
     * @param <V>
     */
    public static <K, V> Map<K, V> newMap() {
        return new HashMap<>();
    }

    /**
     * 创建一个指定长度的HashMap
     * @param initialCapacity 初始容量
     * @return
     * @param <K>
     * @param <V>
     */
    public static <K, V> Map<K, V> newMap(int initialCapacity) {
        return new HashMap<>(initialCapacity);
    }

    /**
     * 创建一个新的LinkedHashMap
     * @return
     * @param <K>
     * @param <V>
     */
    public static <K, V> Map<K, V> newLinkedMap() {
        return new LinkedHashMap<>();
    }

    /**
     * 创建一个新的MultiMap
     * @return
     * @param <K>
     * @param <V>
     */
    public static <K, V> MultiMap<K, V> newMultiMap() {
        return newMultiMap(newMap(), Sets::newHybridSet);
    }


}
