package net.notjustanna.lin.ast.node.value

import net.notjustanna.lin.ast.node.Expr
import net.notjustanna.lin.ast.visitor.NodeMapVisitor
import net.notjustanna.lin.ast.visitor.NodeVisitor
import net.notjustanna.lin.ast.visitor.NodeVisitor1
import net.notjustanna.lin.ast.visitor.NodeVisitorR
import net.notjustanna.tartar.api.lexer.Section

public data class ThisExpr(override val section: Section? = null) : Expr {
    /* @automation(ast.impl ThisExpr,Expr)-start */
    override fun accept(visitor: NodeVisitor) {
        visitor.visitThisExpr(this)
    }

    override fun accept(visitor: NodeMapVisitor): Expr {
        return visitor.visitThisExpr(this)
    }

    override fun <R> accept(visitor: NodeVisitorR<R>): R {
        return visitor.visitThisExpr(this)
    }

    override fun <T> accept(visitor: NodeVisitor1<T>, param0: T) {
        visitor.visitThisExpr(this, param0)
    }
    /* @automation-end */
}
