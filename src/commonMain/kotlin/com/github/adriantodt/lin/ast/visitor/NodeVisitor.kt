package com.github.adriantodt.lin.ast.visitor

import com.github.adriantodt.lin.ast.node.value.ArrayExpr
import com.github.adriantodt.lin.ast.node.access.AssignNode
import com.github.adriantodt.lin.ast.node.misc.BinaryOperation
import com.github.adriantodt.lin.ast.node.value.BooleanExpr
import com.github.adriantodt.lin.ast.node.control.BreakExpr
import com.github.adriantodt.lin.ast.node.control.ContinueExpr
import com.github.adriantodt.lin.ast.node.value.DecimalExpr
import com.github.adriantodt.lin.ast.node.declare.DeclareFunctionExpr
import com.github.adriantodt.lin.ast.node.declare.DeclareVariableNode
import com.github.adriantodt.lin.ast.node.control.DoWhileNode
import com.github.adriantodt.lin.ast.node.misc.EnsureNotNullExpr
import com.github.adriantodt.lin.ast.node.control.ForNode
import com.github.adriantodt.lin.ast.node.value.FunctionExpr
import com.github.adriantodt.lin.ast.node.access.IdentifierExpr
import com.github.adriantodt.lin.ast.node.control.IfExpr
import com.github.adriantodt.lin.ast.node.control.IfNode
import com.github.adriantodt.lin.ast.node.value.IntegerExpr
import com.github.adriantodt.lin.ast.node.InvalidNode
import com.github.adriantodt.lin.ast.node.invoke.InvokeExpr
import com.github.adriantodt.lin.ast.node.invoke.InvokeLocalExpr
import com.github.adriantodt.lin.ast.node.invoke.InvokeMemberExpr
import com.github.adriantodt.lin.ast.node.MultiExpr
import com.github.adriantodt.lin.ast.node.MultiNode
import com.github.adriantodt.lin.ast.node.value.NullExpr
import com.github.adriantodt.lin.ast.node.value.ObjectExpr
import com.github.adriantodt.lin.ast.node.access.PropertyAccessExpr
import com.github.adriantodt.lin.ast.node.access.PropertyAssignNode
import com.github.adriantodt.lin.ast.node.control.ReturnExpr
import com.github.adriantodt.lin.ast.node.value.StringExpr
import com.github.adriantodt.lin.ast.node.access.SubscriptAccessExpr
import com.github.adriantodt.lin.ast.node.access.SubscriptAssignNode
import com.github.adriantodt.lin.ast.node.value.ThisExpr
import com.github.adriantodt.lin.ast.node.control.ThrowExpr
import com.github.adriantodt.lin.ast.node.control.TryExpr
import com.github.adriantodt.lin.ast.node.misc.TypeofExpr
import com.github.adriantodt.lin.ast.node.misc.UnaryOperation
import com.github.adriantodt.lin.ast.node.control.WhileNode

/**
 * A Node Visitor with no parameters and no return value.
 * NOTE: This file is generated!
 */
interface NodeVisitor {
    fun visitArrayExpr(node: ArrayExpr)

    fun visitAssignNode(node: AssignNode)

    fun visitBinaryOperation(node: BinaryOperation)

    fun visitBooleanExpr(node: BooleanExpr)

    fun visitBreakExpr(node: BreakExpr)

    fun visitContinueExpr(node: ContinueExpr)

    fun visitDecimalExpr(node: DecimalExpr)

    fun visitDeclareFunctionExpr(node: DeclareFunctionExpr)

    fun visitDeclareVariableNode(node: DeclareVariableNode)

    fun visitDoWhileNode(node: DoWhileNode)

    fun visitEnsureNotNullExpr(node: EnsureNotNullExpr)

    fun visitForNode(node: ForNode)

    fun visitFunctionExpr(node: FunctionExpr)

    fun visitIdentifierExpr(node: IdentifierExpr)

    fun visitIfExpr(node: IfExpr)

    fun visitIfNode(node: IfNode)

    fun visitIntegerExpr(node: IntegerExpr)

    fun visitInvalidNode(node: InvalidNode)

    fun visitInvokeExpr(node: InvokeExpr)

    fun visitInvokeLocalExpr(node: InvokeLocalExpr)

    fun visitInvokeMemberExpr(node: InvokeMemberExpr)

    fun visitMultiExpr(node: MultiExpr)

    fun visitMultiNode(node: MultiNode)

    fun visitNullExpr(node: NullExpr)

    fun visitObjectExpr(node: ObjectExpr)

    fun visitPropertyAccessExpr(node: PropertyAccessExpr)

    fun visitPropertyAssignNode(node: PropertyAssignNode)

    fun visitReturnExpr(node: ReturnExpr)

    fun visitStringExpr(node: StringExpr)

    fun visitSubscriptAccessExpr(node: SubscriptAccessExpr)

    fun visitSubscriptAssignNode(node: SubscriptAssignNode)

    fun visitThisExpr(node: ThisExpr)

    fun visitThrowExpr(node: ThrowExpr)

    fun visitTryExpr(node: TryExpr)

    fun visitTypeofExpr(node: TypeofExpr)

    fun visitUnaryOperation(node: UnaryOperation)

    fun visitWhileNode(node: WhileNode)
}
