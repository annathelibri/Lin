package io.github.cafeteriaguild.lin.ast.expr.nodes

import net.notjustanna.tartar.api.lexer.Section
import io.github.cafeteriaguild.lin.ast.expr.AbstractNode
import io.github.cafeteriaguild.lin.ast.expr.Expr
import io.github.cafeteriaguild.lin.ast.expr.NodeParamVisitor
import io.github.cafeteriaguild.lin.ast.expr.NodeVisitor

class ThrowExpr(val value: Expr, section: Section) : AbstractNode(section), Expr {
    // NOTE by NotJustAnna:
    // "return" and "throw" is a Node, not an Expr.
    // You can val a = return b

    override fun <R> accept(visitor: NodeVisitor<R>) = visitor.visit(this)
    override fun <T, R> accept(visitor: NodeParamVisitor<T, R>, param: T) = visitor.visit(this, param)
}