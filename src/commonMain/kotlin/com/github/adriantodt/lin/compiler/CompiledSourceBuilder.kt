package com.github.adriantodt.lin.compiler

import com.github.adriantodt.lin.bytecode.CompiledFunction
import com.github.adriantodt.lin.bytecode.CompiledParameter
import com.github.adriantodt.lin.bytecode.CompiledSource

class CompiledSourceBuilder {
    private val longPool = mutableListOf<Long>()
    private val stringPool = mutableListOf<String>()

    private val functionParameters = mutableListOf<List<CompiledParameter>>()
    private val functions = mutableListOf<CompiledFunction>()

    private val nodeBuilders = mutableListOf<CompiledNodeBuilder>()

    fun newNodeBuilder(): CompiledNodeBuilder {
        val builder = CompiledNodeBuilder(this, nodeBuilders.size)
        nodeBuilders += builder
        return builder
    }

    fun constantId(value: String): Int {
        val indexOf = stringPool.indexOf(value)
        if (indexOf != -1) return indexOf
        stringPool.add(value)
        return stringPool.lastIndex
    }

    fun constantId(value: Double): Int {
        return constantId(value.toBits())
    }

    fun constantId(value: Long): Int {
        val indexOf = longPool.indexOf(value)
        if (indexOf != -1) return indexOf
        longPool.add(value)
        return stringPool.lastIndex
    }

    fun registerFunction(parametersId: Int, name: String?, bodyId: Int): Int {
        functions += CompiledFunction(parametersId, name?.let(this::constantId) ?: -1, bodyId)
        return functions.lastIndex
    }

    fun registerParameters(parameters: List<CompiledParameter>): Int {
        functionParameters += parameters
        return functionParameters.lastIndex
    }

    fun build() = CompiledSource(
        longPool.toList(),
        stringPool.toList(),
        functionParameters.toList(),
        functions.toList(),
        nodeBuilders.map { it.build() }
    )
}
