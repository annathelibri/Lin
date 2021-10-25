package com.github.adriantodt.lin.ast.viewer

import com.github.adriantodt.lin.ast.node.InvalidNode
import com.github.adriantodt.lin.ast.node.MultiExpr
import com.github.adriantodt.lin.ast.node.MultiNode
import com.github.adriantodt.lin.ast.node.access.*
import com.github.adriantodt.lin.ast.node.control.*
import com.github.adriantodt.lin.ast.node.declare.DeclareFunctionExpr
import com.github.adriantodt.lin.ast.node.declare.DeclareVariableNode
import com.github.adriantodt.lin.ast.node.invoke.InvokeExpr
import com.github.adriantodt.lin.ast.node.invoke.InvokeExtensionExpr
import com.github.adriantodt.lin.ast.node.invoke.InvokeLocalExpr
import com.github.adriantodt.lin.ast.node.invoke.InvokeMemberExpr
import com.github.adriantodt.lin.ast.node.misc.BinaryOperation
import com.github.adriantodt.lin.ast.node.misc.EnsureNotNullExpr
import com.github.adriantodt.lin.ast.node.misc.TypeofExpr
import com.github.adriantodt.lin.ast.node.misc.UnaryOperation
import com.github.adriantodt.lin.ast.node.value.*
import com.github.adriantodt.lin.ast.visitor.NodeVisitor0

class NodeTreePrinter(private val builder: StringBuilder = StringBuilder()) : NodeVisitor0, Appendable by builder {
    var indent = 0
    var name: String? = null

    private fun appendIndent() {
        for (ignored in 0 until indent) {
            append(' ')
        }
    }

    private fun appendName() {
        if (name != null) {
            append(name)
            append(": ")
        }
    }

    private inline fun indenting(block: () -> Unit) {
        indent += 2
        block()
        indent -= 2
    }

    override fun visitTypeofExpr(node: TypeofExpr) {
        appendIndent()
        appendName()
        appendLine("TypeofExpr ${node.section} {")
        indenting {
            name = "value"
            node.value.accept(this)
        }
        appendLine('}')
    }

    override fun visitUnaryOperation(node: UnaryOperation) {
        appendIndent()
        appendName()
        appendLine("UnaryOperation ${node.operator} ${node.section} {")
        indenting {
            name = "target"
            node.target.accept(this)
        }
        appendLine('}')
    }

    override fun visitSubscriptAccessExpr(node: SubscriptAccessExpr) {
        TODO("Not yet implemented")
    }

    override fun visitPropertyAccessExpr(node: PropertyAccessExpr) {
        TODO("Not yet implemented")
    }

    override fun visitSubscriptAssignNode(node: SubscriptAssignNode) {
        TODO("Not yet implemented")
    }

    override fun visitBinaryOperation(node: BinaryOperation) {
        TODO("Not yet implemented")
    }

    override fun visitDeclareVariableNode(node: DeclareVariableNode) {
        TODO("Not yet implemented")
    }

    override fun visitEnsureNotNullExpr(node: EnsureNotNullExpr) {
        TODO("Not yet implemented")
    }

    override fun visitAssignNode(node: AssignNode) {
        TODO("Not yet implemented")
    }

    override fun visitPropertyAssignNode(node: PropertyAssignNode) {
        TODO("Not yet implemented")
    }

    override fun visitIdentifierExpr(node: IdentifierExpr) {
        appendIndent()
        appendName()
        appendLine("IdentifierExpr ${node.section} = ${node.name}")
    }

    override fun visitInvalidNode(node: InvalidNode) {
        TODO("Not yet implemented")
    }

    override fun visitMultiExpr(node: MultiExpr) {
        appendIndent()
        appendName()
        appendLine("MultiExpr ${node.section} {")
        indenting {
            var i = 0
            for (each in node.list) {
                name = "node ${i++}"
                each.accept(this)
            }
            name = "node $i"
            node.last.accept(this)
        }
        appendLine('}')
    }

    override fun visitInvokeLocalExpr(node: InvokeLocalExpr) {
        TODO("Not yet implemented")
    }

    override fun visitInvokeMemberExpr(node: InvokeMemberExpr) {
        TODO("Not yet implemented")
    }

    override fun visitMultiNode(node: MultiNode) {
        TODO("Not yet implemented")
    }

    override fun visitInvokeExpr(node: InvokeExpr) {
        TODO("Not yet implemented")
    }

    override fun visitBooleanExpr(node: BooleanExpr) {
        appendIndent()
        appendName()
        appendLine("BooleanExpr ${node.section} = ${node.value}")
    }

    override fun visitIntExpr(node: IntExpr) {
        appendIndent()
        appendName()
        appendLine("IntExpr ${node.section} = ${node.value}")
    }

    override fun visitArrayExpr(node: ArrayExpr) {
        appendIndent()
        appendName()
        appendLine("ArrayExpr ${node.section} {")
        indenting {
            var i = 0
            for (each in node.value) {
                name = "entry ${i++}"
                each.accept(this)
            }
        }
        appendIndent()
        appendLine('}')
    }

    override fun visitThisExpr(node: ThisExpr) {
        appendIndent()
        appendName()
        appendLine("ThisExpr ${node.section}")
    }

    override fun visitFloatExpr(node: FloatExpr) {
        appendIndent()
        appendName()
        appendLine("FloatExpr ${node.section} = ${node.value}")
    }

    override fun visitNullExpr(node: NullExpr) {
        appendIndent()
        appendName()
        appendLine("NullExpr ${node.section}")
    }

    override fun visitFunctionExpr(node: FunctionExpr) {
        TODO("Not yet implemented")
    }

    override fun visitInvokeExtensionExpr(node: InvokeExtensionExpr) {
        TODO("Not yet implemented")
    }

    override fun visitLongExpr(node: LongExpr) {
        appendIndent()
        appendName()
        appendLine("LongExpr ${node.section} = ${node.value}")
    }

    override fun visitObjectExpr(node: ObjectExpr) {
        appendIndent()
        appendName()
        appendLine("ObjectExpr ${node.section} {")
        indenting {
            var i = 0
            for ((key, value) in node.value) {
                name = (i++).toString()
                appendIndent()
                appendLine("entry $i: {")
                indenting {
                    name = "key"
                    key.accept(this)
                    name = "value"
                    value.accept(this)
                }
                appendIndent()
                appendLine('}')
            }
        }
        appendIndent()
        appendLine('}')
    }

    override fun visitDoubleExpr(node: DoubleExpr) {
        appendIndent()
        appendName()
        appendLine("DoubleExpr ${node.section} = ${node.value}")
    }

    override fun visitIfExpr(node: IfExpr) {
        TODO("Not yet implemented")
    }

    override fun visitTryExpr(node: TryExpr) {
        TODO("Not yet implemented")
    }

    override fun visitCharExpr(node: CharExpr) {
        appendIndent()
        appendName()
        appendLine("CharExpr ${node.section} = '${node.value}'")
    }

    override fun visitStringExpr(node: StringExpr) {
        appendIndent()
        appendName()
        appendLine("StringExpr ${node.section} = \"${node.value}\"")
    }

    override fun visitIfNode(node: IfNode) {
        TODO("Not yet implemented")
    }

    override fun visitReturnExpr(node: ReturnExpr) {
        TODO("Not yet implemented")
    }

    override fun visitThrowExpr(node: ThrowExpr) {
        TODO("Not yet implemented")
    }

    override fun visitWhileNode(node: WhileNode) {
        TODO("Not yet implemented")
    }

    override fun visitForNode(node: ForNode) {
        TODO("Not yet implemented")
    }

    override fun visitContinueExpr(node: ContinueExpr) {
        TODO("Not yet implemented")
    }

    override fun visitDeclareFunctionExpr(node: DeclareFunctionExpr) {
        TODO("Not yet implemented")
    }

    override fun visitUnitExpr(node: UnitExpr) {
        appendIndent()
        appendName()
        appendLine("UnitExpr ${node.section}")
    }

    override fun visitDoWhileNode(node: DoWhileNode) {
        TODO("Not yet implemented")
    }

    override fun visitBreakExpr(node: BreakExpr) {
        TODO("Not yet implemented")
    }
}
