package org.example.utils.graph;

import pascal.taie.language.type.ClassType;

/**
 * CFG的边
 * @param <N>  CFG的节点
 */
public class CFGEdge<N> extends AbstractEdge<N>{

    public enum Kind {

        /**
         * ENTRY到真正的开始节点的边
         */
        ENTRY,

        /**
         * FALL_THROUGH到下一个语句的边
         */
        FALL_THROUGH,

        /**
         * goto语句的边
         */
        GOTO,

        /**
         * if语句的边，条件为true
         */
        IF_TRUE,

        /**
         * if语句的边，条件为false
         */
        IF_FALSE,

        /**
         * switch语句的边，显式case
         */
        SWITCH_CASE,

        /**
         * switch语句的边，default case
         */
        SWITCH_DEFAULT,

        /**
         * 从引发异常的节点到异常的显式处理程序的异常控制流的边
         */
        CAUGHT_EXCEPTION,

        /**
         * 从引发异常的节点到未捕获异常处理程序的异常控制流的边
         * 这些边总是从引发异常的节点到CFG的exit节点
         */
        UNCAUGHT_EXCEPTION,

        /**
         * 到return语句的边
         */
        RETURN,
    }

    private final Kind kind;

    CFGEdge(Kind kind, N source, N target) {
        super(source, target);
        this.kind = kind;
    }

    public Kind getKind() {
        return kind;
    }

    public boolean isSwitchCase() {
        return kind == Kind.SWITCH_CASE;
    }

    public int getCaseValue(){

    }

    public boolean isExceptional(){

    }

    public Set<ClassType> getExceptions(){

    }
}
