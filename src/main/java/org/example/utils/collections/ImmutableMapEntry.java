package org.example.utils.collections;

public class ImmutableMapEntry<K, V> extends MapEntry<K, V>{
    public ImmutableMapEntry(K key, V value) {
        super(key, value);
    }

    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException();
    }
}
