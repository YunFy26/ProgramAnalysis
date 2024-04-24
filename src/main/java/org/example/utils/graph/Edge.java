package org.example.utils.graph;

import java.io.Serializable;

public interface Edge<N> extends Serializable {

    N source();

    N target();
}
