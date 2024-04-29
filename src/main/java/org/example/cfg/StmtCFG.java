package org.example.cfg;

import org.example.utils.collections.Sets;
import pascal.taie.ir.IR;
import pascal.taie.ir.stmt.Stmt;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;


/**
 * CFG，节点为Stmt，这个类提供一个CFG的节点和索引的映射
 * <ul>
 *     <li>入口节点映射到索引0</li>
 *     <li>出口节点映射到索引<code>ir.getStmts().size() + 1</code></li>
 *     <li>其他节点(stmts)映射到索引<code>stmt.getIndex() + 1</code></li>
 * </ul>
 */
class StmtCFG extends AbastractCFG<Stmt> {


    public StmtCFG(IR ir) {
        super(ir);
    }

    @Override
    public Set<Stmt> getNodes() {
        Set<Stmt> stmts = Sets.newOrderedSet(Comparator.comparing(this::getIndex));
        stmts.addAll(super.getNodes());
        return Collections.unmodifiableSet(stmts);
    }

    @Override
    public int getIndex(Stmt stmt) {
        if (isEntry(stmt)) {
            return 0;
        } else if (isExit(stmt)) {
            return ir.getStmts().size() + 1;
        } else {
            return stmt.getIndex() + 1;
        }
    }

    @Override
    public Stmt getNode(int index) {
        if (index == 0) {
            return getEntry();
        } else if (index == ir.getStmts().size() + 1) {
            return getExit();
        } else {
            return ir.getStmt(index - 1);
        }
    }
}
