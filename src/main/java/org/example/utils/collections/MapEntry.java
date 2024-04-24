package org.example.utils.collections;

import org.example.utils.Hashes;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * 存储Map中的键值对pair
 */
public class MapEntry<K, V> implements Map.Entry<K, V>, Serializable {

    private final K key;

    private V value;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    /**
     * Set the value of this entry
     * @param value new value to be stored in this entry
     * @return the old value of this entry
     */
    @Override
    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (!(obj instanceof Map.Entry<?, ?> e)){
            return false;
        }
        // return key.equals(e.getKey()) && value.equals(e.getValue()); // 可能存在NullPointer
        return Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue());
    }

    @Override
    public int hashCode() {
        return Hashes.safeHash(key, value);
    }

    @Override
    public String toString() {
        return "MapEntry{" + "key=" + key + ", value=" + value + '}';
    }
}
