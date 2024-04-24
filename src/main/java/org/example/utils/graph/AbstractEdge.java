package org.example.utils.graph;


import pascal.taie.util.Hashes;

public abstract class AbstractEdge<N> implements Edge<N> {

    private final N source;

    private final N target;

    public AbstractEdge(N source, N target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public N source() {
        return source;
    }

    @Override
    public N target() {
        return target;
    }

    @Override
    public int hashCode() {
        return Hashes.hash(source, target);
    }

    // TODO: AbstractEdge的equals方法
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if
//    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{" + source + " -> " + target + '}';
    }
}
