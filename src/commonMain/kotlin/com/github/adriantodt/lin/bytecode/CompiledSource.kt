package com.github.adriantodt.lin.bytecode

import com.github.adriantodt.lin.bytecode.utils.maxU24
import com.github.adriantodt.lin.bytecode.utils.readU24
import com.github.adriantodt.lin.bytecode.utils.writeU24
import com.github.adriantodt.lin.exception.IllegalConstantIndexException
import com.github.adriantodt.lin.utils.Deserializer
import com.github.adriantodt.lin.utils.Serializable
import okio.Buffer
import okio.ByteString.Companion.encodeUtf8

data class CompiledSource(
    val longPool: List<Long>,
    val stringPool: List<String>,
    val functionParameters: List<List<CompiledParameter>>,
    val functions: List<CompiledFunction>,
    val sections: List<CompiledSection>,
    val nodes: List<CompiledNode>
) : Serializable {
    override fun serializeTo(buffer: Buffer) {
        check(longPool.size < maxU24) {
            "Compiled Source cannot be compiled as the long pool exceeds the maximum size (${longPool.size} >= $maxU24)"
        }
        buffer.writeU24(longPool.size)
        for (l in longPool) buffer.writeLong(l)

        check(stringPool.size < maxU24) {
            "Compiled Source cannot be compiled as the string pool exceeds the maximum size (${stringPool.size} >= $maxU24)"
        }
        buffer.writeU24(stringPool.size)
        for (s in stringPool) {
            val encoded = s.encodeUtf8()
            buffer.writeInt(encoded.size).write(encoded)
        }

        buffer.writeInt(functionParameters.size)
        for (list in functionParameters) {
            buffer.writeInt(list.size)
            for (parameter in list) parameter.serializeTo(buffer)
        }

        check(stringPool.size < maxU24) {
            "Compiled Source cannot be compiled as the function definitions exceeds the maximum size (${functions.size} >= $maxU24)"
        }
        buffer.writeU24(functions.size)
        for (function in functions) function.serializeTo(buffer)

        buffer.writeInt(sections.size)
        for (section in sections) section.serializeTo(buffer)

        buffer.writeInt(nodes.size)
        for (node in nodes) node.serializeTo(buffer)
    }

    fun longConstOrNull(index: Int): Long? {
        return longPool.getOrNull(index)
    }

    fun longConst(index: Int): Long {
        return longConstOrNull(index) ?: throw IllegalConstantIndexException(index)
    }

    fun stringConstOrNull(index: Int): String? {
        return stringPool.getOrNull(index)
    }

    fun stringConst(index: Int): String {
        return stringConstOrNull(index) ?: throw IllegalConstantIndexException(index)
    }

    companion object : Deserializer<CompiledSource> {
        override fun deserializeFrom(buffer: Buffer): CompiledSource {
            val longPool = mutableListOf<Long>()
            repeat(buffer.readU24()) {
                longPool += buffer.readLong()
            }

            val stringPool = mutableListOf<String>()
            repeat(buffer.readU24()) {
                val size = buffer.readInt()
                stringPool += buffer.readByteString(size.toLong()).utf8()
            }

            val functionParameters = mutableListOf<List<CompiledParameter>>()
            repeat(buffer.readInt()) {
                val list = mutableListOf<CompiledParameter>()
                repeat(buffer.readInt()) {
                    list += CompiledParameter.deserializeFrom(buffer)
                }
                functionParameters += list
            }

            val functions = mutableListOf<CompiledFunction>()
            repeat(buffer.readU24()) {
                functions += CompiledFunction.deserializeFrom(buffer)
            }

            val sections = mutableListOf<CompiledSection>()
            repeat(buffer.readInt()) {
                sections += CompiledSection.deserializeFrom(buffer)
            }

            val nodes = mutableListOf<CompiledNode>()
            repeat(buffer.readInt()) {
                nodes += CompiledNode.deserializeFrom(buffer)
            }

            return CompiledSource(longPool, stringPool, functionParameters, functions, sections, nodes)
        }
    }
}
