package io.github.cafeteriaguild.lin.ast.expr.access

import net.notjustanna.tartar.api.lexer.Section
import io.github.cafeteriaguild.lin.ast.expr.*

class PropertyAccessExpr(
    val target: Expr, val nullSafe: Boolean, val name: String, section: Section
) : AbstractNode(section), AccessExpr {
    override fun <R> accept(visitor: NodeVisitor<R>) = visitor.visit(this)
    override fun <T, R> accept(visitor: NodeParamVisitor<T, R>, param: T) = visitor.visit(this, param)
}