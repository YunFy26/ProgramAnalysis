package org.example.utils.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public final class Sets {
    private Sets(){}

    public static <E> Set<E> newSet() {
        return new HashSet<>();
    }

    public static <E> Set<E> newSet(int size) {
        return new HashSet<>(size);
    }

    public static <E> Set<E> newLinkedSet() {
        return new LinkedHashSet<>();
    }

    public static <E> Set<E> newHybridSet() {
        return new HybridHashSet<>();
    }
}
