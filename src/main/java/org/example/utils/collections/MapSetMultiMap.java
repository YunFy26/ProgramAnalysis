package org.example.utils.collections;


import org.example.utils.function.SSupplier;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * {@link MultiMap} 的具体实现类，存储键值对
 * @param <K> 键的类型
 * @param <V> 值的类型
 */
public class MapSetMultiMap<K, V> extends AbstractMultiMap<K, V> implements Serializable {

    private final Map<K, Set<V>> map;

    private final SSupplier<Set<V>> setFactory;

    private int size = 0;


    public  MapSetMultiMap(Map<K, Set<V>> map, SSupplier<Set<V>> setFactory) {
        this.map = map;
        this.setFactory = setFactory;
    }



    @Override
    public boolean contains(K key, V value) {
        return get(key).contains(value);
    }

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public Set<V> get(@Nonnull K key) {
        Objects.requireNonNull(key, NULL_KEY);
        Set<V> values = map.get(key);
        // 如果values为空，返回一个空的不可变的集合（Set.of()），否则返回一个不可变的集合
        return values == null ? Set.of() :
                Collections.unmodifiableSet(values);
    }

    @Override
    public boolean put(@Nonnull K key, @Nonnull V value) {
        Objects.requireNonNull(key, NULL_KEY);
        Objects.requireNonNull(value, NULL_VALUE);

    }

    @Override
    public boolean putAll(@Nonnull K key, @Nonnull Collection<? extends V> values) {
        return false;
    }

    @Override
    public boolean putAll(@Nonnull MultiMap<? extends K, ? extends V> multiMap) {
        return false;
    }

    @Override
    public boolean remove(K key, V value) {
        return false;
    }

    @Override
    public boolean removeAll(K key) {
        return false;
    }

    @Override
    public boolean removeAll(K key, Collection<? extends V> values) {
        return false;
    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    protected Iterator<Map.Entry<K, V>> entryIterator() {
        return null;
    }

    @Override
    public void forEachSet(@Nonnull BiConsumer<K, Collection<V>> action) {

    }
}
