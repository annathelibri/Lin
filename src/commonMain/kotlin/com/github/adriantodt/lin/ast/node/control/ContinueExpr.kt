package com.github.adriantodt.lin.ast.node.control

import com.github.adriantodt.lin.ast.node.Expr
import com.github.adriantodt.lin.ast.visitor.NodeMapVisitor
import com.github.adriantodt.lin.ast.visitor.NodeVisitor
import com.github.adriantodt.lin.ast.visitor.NodeVisitor1
import com.github.adriantodt.lin.ast.visitor.NodeVisitorR
import com.github.adriantodt.tartar.api.lexer.Section

data class ContinueExpr(override val section: Section) : Expr {
    /* @automation(ast.impl ContinueExpr,Expr)-start */
    override fun accept(visitor: NodeVisitor) = visitor.visitContinueExpr(this)

    override fun accept(visitor: NodeMapVisitor): Expr = visitor.visitContinueExpr(this)

    override fun <R> accept(visitor: NodeVisitorR<R>): R = visitor.visitContinueExpr(this)

    override fun <T> accept(visitor: NodeVisitor1<T>, param0: T) = visitor.visitContinueExpr(this, param0)
    /* @automation-end */
}
