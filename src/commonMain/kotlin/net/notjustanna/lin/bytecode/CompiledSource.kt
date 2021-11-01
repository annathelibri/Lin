package net.notjustanna.lin.bytecode

import net.notjustanna.lin.utils.Deserializer
import net.notjustanna.lin.utils.Serializable
import okio.Buffer
import okio.ByteString.Companion.encodeUtf8

data class CompiledSource(
    val longPool: List<Long>,
    val stringPool: List<String>,
    val functionParameters: List<List<CompiledParameter>>,
    val functions: List<CompiledFunction>,
    val nodes: List<CompiledNode>
) : Serializable {
    override fun serializeTo(buffer: Buffer) {
        buffer.writeInt(longPool.size)
        for (l in longPool) buffer.writeLong(l)

        buffer.writeInt(stringPool.size)
        for (s in stringPool) {
            val encoded = s.encodeUtf8()
            buffer.writeInt(encoded.size).write(encoded)
        }

        buffer.writeInt(functionParameters.size)
        for (list in functionParameters) {
            buffer.writeInt(list.size)
            for (parameter in list) parameter.serializeTo(buffer)
        }

        buffer.writeInt(functions.size)
        for (function in functions) function.serializeTo(buffer)

        buffer.writeInt(nodes.size)
        for (node in nodes) node.serializeTo(buffer)
    }


    companion object : Deserializer<CompiledSource> {
        override fun deserializeFrom(buffer: Buffer): CompiledSource {
            val longPool = mutableListOf<Long>()
            repeat(buffer.readInt()) {
                longPool += buffer.readLong()
            }

            val stringPool = mutableListOf<String>()
            repeat(buffer.readInt()) {
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
            repeat(buffer.readInt()) {
                functions += CompiledFunction.deserializeFrom(buffer)
            }

            val nodes = mutableListOf<CompiledNode>()
            repeat(buffer.readInt()) {
                nodes += CompiledNode.deserializeFrom(buffer)
            }

            return CompiledSource(
                longPool, stringPool, functionParameters, functions, nodes
            )
        }
    }
}