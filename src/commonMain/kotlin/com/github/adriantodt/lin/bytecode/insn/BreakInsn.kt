package com.github.adriantodt.lin.bytecode.insn

import com.github.adriantodt.lin.bytecode.utils.writeU24
import okio.Buffer

public object BreakInsn : Insn() {
    override fun serializeTo(buffer: Buffer) {
        buffer.writeByte(Opcode.PARAMETERLESS.ordinal).writeU24(ParameterlessCode.BREAK.ordinal)
    }
}