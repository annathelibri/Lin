package net.notjustanna.lin.ast.node.access

import net.notjustanna.lin.ast.node.Expr
import net.notjustanna.lin.ast.visitor.NodeVisitor0
import net.notjustanna.lin.ast.visitor.NodeVisitor0R
import net.notjustanna.lin.ast.visitor.NodeVisitor1
import net.notjustanna.tartar.api.lexer.Section

data class IdentifierExpr(val name: String, override val section: Section) : Expr {
    /* @automation(ast.impl IdentifierExpr)-start */
    override fun accept(visitor: NodeVisitor0) = visitor.visitIdentifierExpr(this)

    override fun <R> accept(visitor: NodeVisitor0R<R>): R = visitor.visitIdentifierExpr(this)

    override fun <T> accept(visitor: NodeVisitor1<T>, param0: T) = visitor.visitIdentifierExpr(this, param0)
    /* @automation-end */
}
