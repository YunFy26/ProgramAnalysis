package org.example.utils.collections;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


/**
 * 抽象类，扩展了AbstractSetEx类，提供一种特殊的Set，该Set可以根据集合的大小自动切换底层的数据结构<br>
 * 当集合的元素较少时，使用{@link ArraySet}作为底层数据结构<br>
 * 当集合的元素较多时，使用{@link HybridHashSet}作为底层数据结构<br>
 * @param <E> Set中元素的类型
 */
public abstract class AbstractHybridSet<E> extends AbstractSetEx<E> implements Serializable {

    private static final String NULL_MESSAGE = "HybridSet does not permit null element";

    private static final int THRESHOLD = 8;

    protected AbstractHybridSet() {
    }

    protected AbstractHybridSet(Collection<E> c) {
        addAll(c);
    }

    protected int getThreshold(){
        return THRESHOLD;
    }

    protected ArraySet<E> newSmallSet(){
        return new ArraySet<>(getThreshold());
    }

    protected abstract Set<E> newLargeSet(int initCapacity);



    @Override
    @Nonnull
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    @Nonnull
    public <T> T[] toArray(@Nonnull T[] a) {
        return super.toArray(a);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public boolean retainAll(@Nonnull Collection<?> c) {
        return super.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    @Override
    @Nonnull
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public boolean add(E e) {
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return super.addAll(c);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return super.containsAll(c);
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }
}
