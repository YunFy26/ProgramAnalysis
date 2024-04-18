package org.example.utils.dataStructure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 基于ArrayList的Set，用于存储规模较小的数据
 * @param <E>
 */
public class ArraySet<E> extends AbstractSetEx<E> implements Serializable {

    private static final String NULL_MESSAGE = "ArraySet does not permit null element";

    private static final int DEFAULT_CAPACITY = 8;

    private final ArrayList<E> elements;

    private final int initialCapacity;

    // 是否固定容量
    private final boolean fixedCapacity;

    public ArraySet() {
        this(DEFAULT_CAPACITY, true);
    }

    public ArraySet(int initialCapacity) {
        this(initialCapacity, true);
    }

    public ArraySet(int initialCapacity, boolean fixedCapacity) {
        this.initialCapacity = initialCapacity;
        this.fixedCapacity = fixedCapacity;
        elements = new ArrayList<>(initialCapacity);
    }

    // TODO: 2024/4/19 ArraySet的构造函数

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
