package com.github.adriantodt.lin.ast.node.control

import com.github.adriantodt.lin.ast.node.Expr
import com.github.adriantodt.lin.ast.visitor.NodeVisitor0
import com.github.adriantodt.lin.ast.visitor.NodeVisitor0R
import com.github.adriantodt.lin.ast.visitor.NodeVisitor1
import com.github.adriantodt.tartar.api.lexer.Section

data class BreakExpr(override val section: Section) : Expr {
    /* @automation(ast.impl BreakExpr)-start */
    override fun accept(visitor: NodeVisitor0) = visitor.visitBreakExpr(this)

    override fun <R> accept(visitor: NodeVisitor0R<R>): R = visitor.visitBreakExpr(this)

    override fun <T> accept(visitor: NodeVisitor1<T>, param0: T) = visitor.visitBreakExpr(this, param0)
    /* @automation-end */
}
