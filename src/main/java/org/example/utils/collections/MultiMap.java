package org.example.utils.collections;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import javax.annotation.Nonnull;

/**
 * 按照java.util.Map的接口设计，设计多重映射接口
 * 是否存在键值对
 * 获得键值对
 * 添加键值对
 * 移除键值对
 * 返回键、值、键值对的集合
 * 遍历键值对（默认操作，对MultiMap中的每个键值对执行给定的操作）
 * Map的长度
 * Map是否为空
 */

/**
 * 多重映射接口
 *
 * @param <K> 键类型
 * @param <V> 值类型
 */
public interface MultiMap<K, V> {

    /**
     * 是否存在一个键值对（key, value）
     * @param key
     * @param value
     * @return
     */
    boolean contains(K key, V value);

    /**
     * 是否存在一个键为key的键值对
     * @param key
     * @return
     */
    boolean containsKey(K key);

    /**
     * 是否存在一个值为value的键值对
     * @param value
     * @return
     */
    boolean containsValue(V value);

    /**
     * 获取键key对应的值集合
     * @param key
     * @return
     */
    Set<V> get(K key);

    /**
     * 存储键值对
     * @param key
     * @param value
     */
    boolean put(@Nonnull K key, @Nonnull V value);

    /**
     * 将多个值映射到键key
     * @param key
     * @param values
     */
    boolean putAll(@Nonnull K key, @Nonnull Collection<? extends V> values);

    /**
     * 将多重映射中的所有键值对存储到当前映射中
     * @param multiMap
     */
    boolean putAll(@Nonnull MultiMap<? extends K, ? extends V> multiMap);

    /**
     * 移除键值对
     * @param key
     * @param value
     */
    boolean remove(K key, V value);

    /**
     * 移除与键key关联的所有键值对
     * @param key
     */
    boolean removeAll(K key);

    /**
     * 移除键key的一组键值对
     * @param key
     * @param values
     */
    boolean removeAll(K key, Collection<? extends V> values);

    /**
     * 返回映射中所有键的集合
     * @return
     */
    Set<K> keySet();

    /**
     * 返回映射中所有值的集合
     * @return
     */
    Collection<V> values();

    /**
     * 返回映射中所有键值对的集合
     * @return
     */
    Set<Map.Entry<K, V>> entrySet();

    /**
     * {k, {v1, v2, v3}} => {k, v1}, {k, v2}, {k, v3}
     *
     */
    default void forEach(@Nonnull BiConsumer<K, V> action) {
        entrySet().forEach(entry -> action.accept(entry.getKey(), entry.getValue()));
    }

    /**
     * {k, {v1, v2, v3}} => {k， {v1, v2, v3}}
     */
    void forEachSet(@Nonnull BiConsumer<K, Collection<V>> action);

    /**
     * 清空映射
     */
    void clear();

    /**
     * 返回映射的大小
     * @return size of the map
     */
    int size();

    /**
     * 判断映射是否为空
     * @return true
     */
    boolean isEmpty();
}
