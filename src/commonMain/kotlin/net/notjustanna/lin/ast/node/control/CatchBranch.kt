package net.notjustanna.lin.ast.node.control

import net.notjustanna.lin.ast.node.Node

/**
 * This is part of [TryExpr].
 */
public data class CatchBranch(
    val caughtName: String?, val branch: Node
)
