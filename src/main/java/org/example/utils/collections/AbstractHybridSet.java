package org.example.utils.collections;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
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

    private Set<E> set;

    private E singleton;

    private boolean isLargeSet = false;

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

    protected void upgradeToSmallSet(){
        set = newSmallSet();
        if (singleton != null){
            set.add(singleton);
            singleton = null;
        }
    }

    protected void upgradeToLargeSet(int initCapacity){
        assert !isLargeSet;
        Set<E> origin = set;
        set = newLargeSet(initCapacity);
        if (singleton != null){
            set.add(singleton);
            singleton = null;
        }
        if (origin != null){
            set.addAll(origin);
        }
        isLargeSet = true;
    }

    /**
     * 如果singleton不为空，则说明当前集合元素只有一个，再增加一个元素需要转变为set
     * 如果singleton为空，set不为空，则说明当前集合元素大于一个，如果不是大集合，且元素个数+1将会大于阈值，需要转变为大集合再增加
     * 如果singleton为空，set为空，则说明当前集合元素为0，直接将元素赋值给singleton
     * @param e element whose presence in this collection is to be ensured
     * @return true if element is added successfully
     */
    @Override
    public boolean add(E e) {
        Objects.requireNonNull(e, NULL_MESSAGE);
        if (singleton != null){
            if (singleton.equals(e)){
                return false;
            }
            singleton = null;
            upgradeToSmallSet();
        }
        if (set != null){
            if(!isLargeSet && set.size() + 1 > getThreshold()) {
                upgradeToLargeSet(getThreshold() * 2);
            }
            set.add(e);
        }
        singleton = e;
        return true;
    }

    @Override
    public boolean addAll(@Nonnull Collection<? extends E> c){
        if (!isLargeSet){
            int cSize = c.size();
            if (cSize == 0) return false;
            int newSize = cSize + size();
            int threshold = getThreshold();
            if (set == null){
                if (newSize == 1){
                    assert singleton == null;
                    E e = CollectionsUtils.getOne(c);
                    singleton = Objects.requireNonNull(e, NULL_MESSAGE);
                    return true;
                } else if (newSize <= threshold) {
                    upgradeToSmallSet();
                }else {
                    upgradeToLargeSet(newSize);
                }
            }else if(newSize > threshold){
                upgradeToLargeSet(newSize);
            }
            return set.addAll(c);
        }
    }

    @Override
    public int size() {
        if (singleton != null){
            return 1;
        }
        if (set != null){
            return set.size();
        }
        return 0;
    }
}
