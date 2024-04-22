package org.example.utils.collections;

import java.util.Collection;

/**
 * CollectionsUtils，提供一些对集合操作的方法
 */
public final class CollectionsUtils {
    private CollectionsUtils() {
    }

    public static <T> T getOne(Collection<T> c){
        return c.iterator().next();
    }
}
