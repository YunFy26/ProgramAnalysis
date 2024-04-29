package org.example.utils.collections;

import java.util.*;

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

    /**
     * 创建一个有序的Set
     * @return TreeSet
     * @param <E> null
     */
    public static <E extends Comparable<E>> TreeSet<E> newOrderedSet() {
        return new TreeSet<>();
    }

    /**
     * 创建一个有序的Set
     * @param comparator 定义对象比较规则
     * @return TreeSet
     */
    public static <E> TreeSet<E> newOrderedSet(Comparator<? super E> comparator) {
        return new TreeSet<>(comparator);
    }
}
