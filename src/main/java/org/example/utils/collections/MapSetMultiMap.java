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

    // 表示Map中的value的总数
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

    private Set<V> getOrCreateSet(@Nonnull K key){
        // 如果没有这个key，则创建一个Set<V>实例
        return map.computeIfAbsent(key, __ -> setFactory.get());
    }

    @Override
    public boolean put(@Nonnull K key, @Nonnull V value) {
        Objects.requireNonNull(key, NULL_KEY);
        Objects.requireNonNull(value, NULL_VALUE);
        if (getOrCreateSet(key).add(value)){
            ++size;
            return true;
        }
        return false;
    }

    @Override
    public boolean putAll(@Nonnull K key, @Nonnull Collection<? extends V> values) {
        Objects.requireNonNull(key, NULL_KEY);
        Objects.requireNonNull(values);
        if (!values.isEmpty()){
            Set<V> set = getOrCreateSet(key);
            int diff = 0;
            for (V v : values){
                if (set.add(Objects.requireNonNull(v, NULL_VALUE))){
                    ++diff;
                }
            }
            if (diff > 0){
                size += diff;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean putAll(@Nonnull MultiMap<? extends K, ? extends V> multiMap) {
        Objects.requireNonNull(multiMap);
        boolean[] modified = {false};
        multiMap.forEachSet((key, values) -> modified[0] |= putAll(key, values));
        return modified[0];
    }

    @Override
    public boolean remove(K key, V value) {
        Collection<V> values = map.get(key);
        if (values != null && values.remove(value)){
            if (values.isEmpty()){
                map.remove(key);
            }
            --size;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(K key) {
        Set<V> values = map.remove(key);
        if (values != null){
            size -= values.size();
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(K key, Collection<? extends V> values) {
        Collection<V> haveValues = map.get(key);
        if (haveValues != null){
            int currSize = haveValues.size();
            haveValues.removeAll(values);
            int diff = currSize - haveValues.size();
            if (diff > 0){
                size -= diff;
                if (haveValues.isEmpty()){
                    map.remove(key);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<K> keySet() {
        return Collections.unmodifiableSet(map.keySet());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        map.clear();
        size = 0;
    }

    @Override
    public int hashCode(){
        return map.hashCode();
    }

    @Override
    protected Iterator<Map.Entry<K, V>> entryIterator() {
        return new EntryIterator();
    }

    private final class EntryIterator implements Iterator<Map.Entry<K, V>>{

        private final Iterator<Map.Entry<K, Set<V>>> mapIt;

        private Iterator<V> valueIt;

        private K currKey;

        private EntryIterator() {
            this.mapIt = map.entrySet().iterator();
            if (mapIt.hasNext()){
                // var 声明局部变量
                var entry = mapIt.next();
                currKey = entry.getKey();
                valueIt = entry.getValue().iterator();
            }else {
                valueIt = Collections.emptyIterator();
            }

        }

        @Override
        public boolean hasNext() {
            return valueIt.hasNext() || mapIt.hasNext();
        }

        @Override
        public Map.Entry<K, V> next() {
            if(valueIt.hasNext()){
                return new ImmutableMapEntry<>(currKey, valueIt.next());
            }else if (mapIt.hasNext()){
                var entry = mapIt.next();
                currKey = entry.getKey();
                valueIt = entry.getValue().iterator();
                return new ImmutableMapEntry<>(currKey, valueIt.next());
            }else {
                throw new NoSuchElementException();
            }
        }
    }

    @Override
    public void forEachSet(@Nonnull BiConsumer<K, Collection<V>> action) {
        map.forEach(action);
    }
}
