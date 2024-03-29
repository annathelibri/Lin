package net.notjustanna.lin.ast.node.misc

import net.notjustanna.lin.ast.node.Expr
import net.notjustanna.lin.ast.visitor.NodeMapVisitor
import net.notjustanna.lin.ast.visitor.NodeVisitor
import net.notjustanna.lin.ast.visitor.NodeVisitor1
import net.notjustanna.lin.ast.visitor.NodeVisitorR
import net.notjustanna.lin.utils.UnaryOperationType
import net.notjustanna.tartar.api.lexer.Section

public data class UnaryOperation(
    val target: Expr,
    val operator: UnaryOperationType,
    override val section: Section? = null
) : Expr {
    /* @automation(ast.impl UnaryOperation,Expr)-start */
    override fun accept(visitor: NodeVisitor) {
        visitor.visitUnaryOperation(this)
    }

    override fun accept(visitor: NodeMapVisitor): Expr {
        return visitor.visitUnaryOperation(this)
    }

    override fun <R> accept(visitor: NodeVisitorR<R>): R {
        return visitor.visitUnaryOperation(this)
    }

    override fun <T> accept(visitor: NodeVisitor1<T>, param0: T) {
        visitor.visitUnaryOperation(this, param0)
    }
    /* @automation-end */
}
