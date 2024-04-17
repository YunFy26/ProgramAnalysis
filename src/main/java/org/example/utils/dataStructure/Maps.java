package org.example.utils.dataStructure;

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
    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<>();
    }

    /**
     * 创建一个指定长度的HashMap
     * @param size 长度
     * @return
     * @param <K>
     * @param <V>
     */
    public static <K, V> Map<K, V> newHashMap(int size) {
        return new HashMap<>(size);
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
        return new HashMap<>();
    }


}
