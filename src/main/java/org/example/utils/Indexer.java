package org.example.utils;

import java.io.Serializable;
import java.util.Objects;

/**
 * 索引器
 */
public interface Indexer<E> extends Serializable {

    int getIndex(E o);

    E getObject(int index);
}
