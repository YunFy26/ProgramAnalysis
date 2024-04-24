package org.example.utils;

import java.util.Objects;

/**
 * 计算hashCode
 */
// TODO: 完善Hashes工具类
public class Hashes {
    public Hashes() {
    }

    public static int hash(Object o) {
        return Objects.hashCode(o);
    }
    public static int safeHash(Object o1, Object o2) {
        return Objects.hashCode(o1) * 31 + Objects.hashCode(o2);
    }

}
