package org.example.utils.factory;

import org.example.utils.dataStructure.MultiMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class MapFactory {
    private MapFactory(){}

    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<>();
    }

    public static <K, V> Map<K, V> newHashMap(int size) {
        return new HashMap<>(size);
    }

    public static <K, V> Map<K, V> newLinkedMap() {
        return new LinkedHashMap<>();
    }

    public static <K, V> MultiMap<K, V> newMultiMap() {
        return new HashMap<>();
    }


}
