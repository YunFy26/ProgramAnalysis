package org.example.cfg;

import pascal.taie.util.graph.Edge;

public class CFGEdge<N> implements Edge<N> {

    private final N source;

    private final N target;

    public CFGEdge(N source, N target) {
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
    public String toString() {
        return getClass().getSimpleName() +
                "{" + source + " -> " + target + '}';
    }
}
