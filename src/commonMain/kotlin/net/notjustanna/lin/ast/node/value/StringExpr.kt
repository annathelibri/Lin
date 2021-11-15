package net.notjustanna.lin.ast.node.value

import net.notjustanna.lin.ast.node.ConstExpr
import net.notjustanna.lin.ast.node.Expr
import net.notjustanna.lin.ast.visitor.NodeMapVisitor
import net.notjustanna.lin.ast.visitor.NodeVisitor
import net.notjustanna.lin.ast.visitor.NodeVisitor1
import net.notjustanna.lin.ast.visitor.NodeVisitorR
import net.notjustanna.tartar.api.lexer.Section

public data class StringExpr(val value: String, override val section: Section? = null) : ConstExpr {
    /* @automation(ast.impl StringExpr,Expr)-start */
    override fun accept(visitor: NodeVisitor) {
        visitor.visitStringExpr(this)
    }

    override fun accept(visitor: NodeMapVisitor): Expr {
        return visitor.visitStringExpr(this)
    }

    override fun <R> accept(visitor: NodeVisitorR<R>): R {
        return visitor.visitStringExpr(this)
    }

    override fun <T> accept(visitor: NodeVisitor1<T>, param0: T) {
        visitor.visitStringExpr(this, param0)
    }
    /* @automation-end */
}
