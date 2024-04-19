package org.example.utils.collections;

import javax.annotation.Nonnull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

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

    /**
     *
     * @param initialCapacity 初始容量
     * @param fixedCapacity 是否固定容量
     */
    public ArraySet(int initialCapacity, boolean fixedCapacity) {
        this.initialCapacity = initialCapacity;
        this.fixedCapacity = fixedCapacity;
        elements = new ArrayList<>(initialCapacity);
    }


//    /**
//     * 以给定的ArrayList作为元素
//     * @param elements ArrayList
//     * @param fixedCapacity 是否固定容量
//     */
//    public ArraySet(ArrayList<E> elements, boolean fixedCapacity) {
//        this.elements = elements;
//        this.initialCapacity = elements.size();
//        this.fixedCapacity = fixedCapacity;
//    }

//    /**
//     * 以给定的集合作为元素
//     * @param coll 集合
//     */
//    public ArraySet(Collection<? extends E> coll) {
//        this(coll.size(), false);
//        addAll(coll);
//    }

    /**
     * 重写集合的一些方法
     * 是否为空：isEmpty()<br>
     * 单元素操作：contains(Object o), add(E e), remove(Object o) <br>
     * 多元素操作：containsAll(Collection<?> c), addAll(Collection<? extends E> c), removeAll(Collection<?> c), retainAll(Collection<?> c) <br>
     * toArray(), clear(), iterator(), size()
     */
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return elements.contains(o);
    }

    @Override
    public boolean add(@Nonnull E e){
        Objects.requireNonNull(e, NULL_MESSAGE);
        if (!elements.contains(e)) {
            ensureCapacity(size() + 1);
            elements.add(e);
            return true;
        }
        return false;
    }


    @Override
    public boolean remove(Object o){
        return o != null && elements.remove(o);
    }




    @Override
    public SetEx<E> addAllReturnDiff(Collection<? extends E> c) {
        return super.addAllReturnDiff(c);
    }

    public SetEx<E> copy() {
        return super.copy();
    }


    @Override
    @Nonnull
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    @Nonnull
    public Iterator<E> iterator() {
        return elements.iterator();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void clear(){
        elements.clear();
    }

    /**
     * 确保ArraySet容量没有达到上限
     * @param currentCapacity 当前容量
     */
    private void ensureCapacity(int currentCapacity) {
        if (fixedCapacity && currentCapacity > initialCapacity) {
            throw new TooManyElementsException("Capacity of this ArraySet is fixed");
        }
    }
}
