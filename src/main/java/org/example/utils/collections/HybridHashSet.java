package org.example.utils.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 基于HashSet的HybridSet，用于存储规模较大的数据
 * @param <E>
 */
public final class HybridHashSet<E> extends AbstractHybridSet<E> implements Serializable {

    public HybridHashSet() {
    }

    public HybridHashSet(Collection<E> c) {
        super(c);
    }

    @Override
    protected Set<E> newLargeSet(int initCapacity) {
        return new HashSet<E>(initCapacity);
    }

    @Override
    protected SetEx<E> newSet() {
        return new HybridHashSet<>();
    }
}
