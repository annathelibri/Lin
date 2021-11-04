package com.github.adriantodt.lin.test.utils

import com.github.adriantodt.lin.vm.scope.ImmutableMapScope
import com.github.adriantodt.lin.vm.types.LAny
import com.github.adriantodt.lin.vm.types.LFunction
import com.github.adriantodt.lin.vm.types.LNull

@Suppress("MemberVisibilityCanBePrivate")
class TestScope(inputs: List<LAny> = emptyList()) {
    val input = inputs.toMutableList()
    val output = mutableListOf<LAny>()
    val scope = ImmutableMapScope(mapOf(
        "retrieve" to LFunction.Native { input.removeFirst() },
        "publish" to LFunction.Native { output.addAll(it); LNull }
    ), null)

    operator fun component1() = input
    operator fun component2() = output
    operator fun component3() = scope
}