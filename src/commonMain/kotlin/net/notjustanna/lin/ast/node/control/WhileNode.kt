package net.notjustanna.lin.ast.node.control

import net.notjustanna.lin.ast.node.Expr
import net.notjustanna.lin.ast.node.Node
import net.notjustanna.lin.ast.visitor.NodeMapVisitor
import net.notjustanna.lin.ast.visitor.NodeVisitor
import net.notjustanna.lin.ast.visitor.NodeVisitor1
import net.notjustanna.lin.ast.visitor.NodeVisitorR
import net.notjustanna.tartar.api.lexer.Section

public data class WhileNode(val condition: Expr, val body: Node?, override val section: Section? = null) : Node {
    /* @automation(ast.impl WhileNode,Node)-start */
    override fun accept(visitor: NodeVisitor) {
        visitor.visitWhileNode(this)
    }

    override fun accept(visitor: NodeMapVisitor): Node {
        return visitor.visitWhileNode(this)
    }

    override fun <R> accept(visitor: NodeVisitorR<R>): R {
        return visitor.visitWhileNode(this)
    }

    override fun <T> accept(visitor: NodeVisitor1<T>, param0: T) {
        visitor.visitWhileNode(this, param0)
    }
    /* @automation-end */
}
