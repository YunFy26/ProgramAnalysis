package org.example.cfg;

import org.example.utils.Indexer;
import pascal.taie.analysis.graph.cfg.CFG;

/**
 * CFG节点索引器
 * record类：Java 14新特性，一般用来创建只包含数据的类，这类只包含数据没有任何行为
 */
public record CFGNodeIndexer<Node>(CFG<Node> cfg) implements Indexer<Node> {

        @Override
        public int getIndex(Node node) {
            return cfg.getIndex(node);
        }

        @Override
        public Node getObject(int index) {
            return cfg.getNode(index);
        }
}
