package com.github.adriantodt.lin.bytecode

import com.github.adriantodt.lin.utils.Deserializer
import com.github.adriantodt.lin.utils.Serializable
import okio.Buffer

data class CompiledFunction(val parametersId: Int, val nameConst: Int, val bodyId: Int) : Serializable {
    override fun serializeTo(buffer: Buffer) {
        buffer.writeInt(parametersId).writeInt(nameConst).writeInt(bodyId)
    }

    companion object : Deserializer<CompiledFunction> {
        override fun deserializeFrom(buffer: Buffer): CompiledFunction {
            return CompiledFunction(buffer.readInt(), buffer.readInt(), buffer.readInt())
        }
    }
}