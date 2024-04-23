package org.example.utils.function;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * Supplier接口的序列化版本
 * @param <T>
 */
@FunctionalInterface
public interface SSupplier<T> extends Supplier<T>, Serializable {

}
