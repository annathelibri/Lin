package com.github.adriantodt.lin.ast.node.access

import com.github.adriantodt.lin.ast.node.Expr
import com.github.adriantodt.lin.ast.node.Node
import com.github.adriantodt.lin.ast.visitor.NodeMapVisitor
import com.github.adriantodt.lin.ast.visitor.NodeVisitor
import com.github.adriantodt.lin.ast.visitor.NodeVisitor1
import com.github.adriantodt.lin.ast.visitor.NodeVisitorR
import com.github.adriantodt.tartar.api.lexer.Section

data class PropertyAssignNode(
    val target: Expr,
    val nullSafe: Boolean,
    val name: String,
    val value: Expr,
    override val section: Section? = null
) : Node {
    /* @automation(ast.impl PropertyAssignNode,Node)-start */
    override fun accept(visitor: NodeVisitor) = visitor.visitPropertyAssignNode(this)

    override fun accept(visitor: NodeMapVisitor): Node = visitor.visitPropertyAssignNode(this)

    override fun <R> accept(visitor: NodeVisitorR<R>): R = visitor.visitPropertyAssignNode(this)

    override fun <T> accept(visitor: NodeVisitor1<T>, param0: T) = visitor.visitPropertyAssignNode(this, param0)
    /* @automation-end */
}
