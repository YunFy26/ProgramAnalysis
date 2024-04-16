package org.example.utils.dataStructure;

import lombok.NonNull;

import java.util.*;

public abstract class AbstractMultiMap<K, V> implements MultiMap<K, V>{

    protected static final String NULL_KEY = "MultiMap does not permit null keys";

    protected static final String NULL_VALUE = "MultiMap does not permit null values";

    /**
     * 判断是否包含键值对
     * @param value
     * @return
     */
    @Override
    public boolean containsValue(V value) {
        for (K key : keySet()){
            if (get(key).contains(value)){
                return true;
            }
        }
        return false;
    }

    /**
     * 缓存entrySet，存储键值对
     * 如果entrySet为空，则创建一个新的EntrySet
     */
    private transient Set<Map.Entry<K, V>> entrySet;

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        var es = entrySet;
        if (es == null){
            es = Collections.unmodifiableSet(new EntrySet());
            entrySet = es;
        }
        return es;
    }

    /**
     * EntrySet类  MultiMap中键值对的集合
     * 重写了contains方法，判断是否包含键值对
     * 重写了iterator方法，用于迭代MultiMap
     */
    private final class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public boolean contains(Object o) {
            if (o instanceof Map.Entry<?, ?> entry){
                return AbstractMultiMap.this.contains((K) entry.getKey(), (V) entry.getValue());
            }
            return false;
        }

        @Override
        @NonNull
        public Iterator<Map.Entry<K, V>> iterator() {
            return entryIterator();
        }

        @Override
        public int size() {
            return AbstractMultiMap.this.size();
        }
    }

    /**
     * 抽象迭代器，用于迭代MultiMap中所有的键值对
     * @return
     */
    protected abstract Iterator<Map.Entry<K, V>> entryIterator();

    /**
     * 缓存values，存储MultiMap中所有的值
     */
    private transient Collection<V> values;

    @Override
    public Collection<V> values() {
        Collection<V> vals = values;
        if (vals == null){
            vals = Views.toMappedCollection(entrySet(), Map.Entry::getValue, o -> containsValue((V) o));
            values = vals;
        }
        return vals;
    }
}
