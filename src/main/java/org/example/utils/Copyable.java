package org.example.utils;

/**
 *
 * @param <T>
 */
public interface Copyable<T> {
    /**
     *
     * @return a copy of this object
     */
    T copy();
}
