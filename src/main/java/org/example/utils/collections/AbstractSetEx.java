package org.example.utils.collections;


import java.util.AbstractSet;
import java.util.Collection;

/**
 * 扩展AbstractSet，实现SetEx接口
 * @param <E>
 */
public abstract class AbstractSetEx<E> extends AbstractSet<E> implements SetEx<E> {

    /**
     * 重写接口SetEx中的addAllReturnDiff方法
     * @param c 目标集合c
     * @return 差集
     */
    @Override
    public SetEx<E> addAllReturnDiff(Collection<? extends E> c) {
        SetEx<E> diff = newSet();
        for (E e : c) {
            if (add(e)) {
                diff.add(e);
            }
        }
        return diff;
    }

    /**
     * 重写接口Copyable中的copy方法
     * @return 当前集合的拷贝
     */
    @Override
    public SetEx<E> copy() {
        SetEx<E> copy = newSet();
        copy.addAll(this);
        return copy;
    }

    /**
     * 创建一个新的Set，需要在子类中实现
     * @return 新的Set
     */
    protected SetEx<E> newSet(){
        throw new UnsupportedOperationException();
    };
}
