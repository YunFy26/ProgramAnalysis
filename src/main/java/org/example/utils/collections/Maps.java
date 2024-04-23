package org.example.utils.collections;

import org.example.utils.function.SSupplier;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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

    public static <K, V> MultiMap<K, V> newMultiMap(Map<K, Set<V>> map) {
        return newMultiMap(map, Sets::newHybridSet);
    }

    public static <K, V> MultiMap<K, V> newMultiMap(Map<K, Set<V>> map, SSupplier<Set<V>> setFactory) {
        return new MapSetMultiMap<>(map, setFactory);
    }

    public static <K, V> MultiMap<K, V> newMultiMap(SSupplier<Set<V>> setFactory) {
        return newMultiMap(newMap(), setFactory);
    }

    public static <K, V> MultiMap<K, V> newMultiMap(int initialCapacity) {
        return newMultiMap(newMap(initialCapacity), Sets::newHybridSet);
    }
}
