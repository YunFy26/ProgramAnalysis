package org.example.utils.factory;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public final class SetFactory {
    private SetFactory(){}

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
