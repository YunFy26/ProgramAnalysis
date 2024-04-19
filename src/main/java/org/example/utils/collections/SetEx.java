package org.example.utils.collections;


import org.example.utils.Copyable;

import java.util.Collection;
import java.util.Set;

/**
 * 扩展Set接口
 * @param <E> Set中元素的类型
 */
public interface SetEx<E> extends Set<E>, Copyable<SetEx<E>> {
    /**
     * 添加集合c中的所有元素，并返回（调用了此方法的集合）和（目标集合c）的差集
     * @param c 目标集合c
     * @return （调用了此方法的集合）和（目标集合c）的差集
     */
    SetEx<E> addAllReturnDiff(Collection<? extends E> c);

}
