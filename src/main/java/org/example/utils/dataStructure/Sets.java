package org.example.utils.dataStructure;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public final class Sets {
    private Sets(){}

    public static <E> Set<E> newHashSet() {
        return new HashSet<>();
    }

    public static <E> Set<E> newHashSet(int size) {
        return new HashSet<>(size);
    }

    public static <E> Set<E> newLinkedSet() {
        return new LinkedHashSet<>();
    }
}
