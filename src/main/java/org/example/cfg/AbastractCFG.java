package org.example.cfg;

import org.example.utils.collections.MultiMap;

import org.example.utils.collections.Maps;
import org.example.utils.collections.Sets;
import org.example.utils.collections.Views;
import pascal.taie.analysis.graph.cfg.CFG;
import pascal.taie.analysis.graph.cfg.CFGEdge;
import pascal.taie.ir.IR;
import pascal.taie.language.classes.JMethod;

import java.util.Collections;
import java.util.Set;

/**
 * 控制流图
 *
 */
public abstract class AbastractCFG<N> implements CFG<N> {

    private final IR ir;

    private N entry;

    private N exit;

    private final Set<N> nodes;

    private final MultiMap<N, CFGEdge<N>> inEdges;

    private final MultiMap<N, CFGEdge<N>> outEdges;

    AbastractCFG(IR ir) {
        this.ir = ir;
        int numNodes = ir.getStmts().size() + 2;
        this.nodes = Sets.newSet(numNodes);
        this.inEdges = Maps.newMultiMap(numNodes);
        this.outEdges = Maps.newMultiMap(numNodes);
    }

    @Override
    // 获取IR
    public IR getIR() {
        return ir;
    }

    @Override
    // 获取方法
    public JMethod getMethod() {
        return ir.getMethod();
    }

    // 设置入口节点
    void setEntry(N entry) {
        assert this.entry == null : "CFG entry should be set only once";
        this.entry = entry;
        nodes.add(entry);
    }
    @Override
    // 获取入口节点
    public N getEntry() {
        return entry;
    }

    // 设置出口节点
    void setExit(N exit) {
        assert this.exit == null : "CFG exit should be set only once";
        this.exit = exit;
        nodes.add(exit);
    }
    @Override
    // 获取出口
    public N getExit() {
        return exit;
    }

    @Override
    // 是否是入口节点
    public boolean isEntry(N node) {
        return node == entry;
    }

    @Override
    // 是否是出口节点
    public boolean isExit(N node) {
        return node == exit;
    }



    @Override
    // 获取前驱节点
    public Set<N> getPredsOf(N node) {
        return Views.toMappedSet(getInEdgesOf(node), CFGEdge::source);
    }

    @Override
    // 获取后继节点
    public Set<N> getSuccsOf(N node) {
        return Set.of();
    }

    @Override
    // 获取入边
    public Set<CFGEdge<N>> getInEdgesOf(N node) {
        return inEdges.get(node);
    }

    @Override
    // 获取出边
    public Set<CFGEdge<N>> getOutEdgesOf(N node) {
        return outEdges.get(node);
    }

    @Override
    // 获取所有节点
    public Set<N> getNodes() {
        return Collections.unmodifiableSet(nodes);
    }
}
