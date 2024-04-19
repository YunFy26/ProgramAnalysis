package org.example.utils.collections;

import javax.annotation.Nonnull;

import java.util.*;

/**
 * MultiMap接口的抽象实现，主要实现可供多重映射类通用的方法
 * @param <K>
 * @param <V>
 */
public abstract class AbstractMultiMap<K, V> implements MultiMap<K, V>{

    protected static final String NULL_KEY = "MultiMap does not permit null keys";

    protected static final String NULL_VALUE = "MultiMap does not permit null values";

    /**
     * 判断是否包含value
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
     * entrySet，存储MultiMap中所有的键值对
     * 如果entrySet为空，则创建一个新的EntrySet
     * EntrySet类  MultiMap中键值对的集合
     * 重写了contains方法，判断是否包含键值对
     * 重写了iterator方法，用于迭代MultiMap
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

    private final class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public boolean contains(Object o) {
            if (o instanceof Map.Entry<?, ?> entry){
                return AbstractMultiMap.this.contains((K) entry.getKey(), (V) entry.getValue());
            }
            return false;
        }

        @Override
        @Nonnull
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
     * 需要在具体的多重映射类中实现entryIterator方法
     * @return
     */
    protected abstract Iterator<Map.Entry<K, V>> entryIterator();

    /**
     * values，存储MultiMap中所有的值
     * values() 返回一个不可变的集合，包含MultiMap中所有的值
     */
    private transient Collection<V> values;

    @Override
    public Collection<V> values() {
        Collection<V> vals = values;
        if (vals == null){
            vals = Views.toMappedCollection(entrySet(), Map.Entry::getValue, obj -> containsValue((V) obj));
            values = vals;
        }
        return vals;
    }

    /**
     * 判断MultiMap是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 判断两个MultiMap是否相等
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (!(obj instanceof MultiMap<?, ?>)){
            return false;
        }
        @SuppressWarnings("unchecked")
        MultiMap<K, V> obj1 = (MultiMap<K, V>) obj;
        if (obj1.size() != size()){
            return false;
        }
        try {
            for (K key : keySet()){
                if (obj1.get(key).equals(get(key))){
                    return false;
                }
            }
        }catch (ClassCastException | NullPointerException e){
            return false;
        }
        return true;
    }

    /**
     * 打印MultiMap
     * @return
     */
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "{", "}");
        for (K key : keySet()){
            joiner.add(key + "=" + get(key));
        }
        return joiner.toString();
    }
}
