package com.github.adriantodt.lin.bytecode.insn

import com.github.adriantodt.lin.bytecode.utils.requireU24
import com.github.adriantodt.lin.bytecode.utils.writeU24
import okio.Buffer

public data class DeclareVariableInsn(val nameConst: Int, val mutable: Boolean) : Insn() {
    override fun serializeTo(buffer: Buffer) {
        buffer.writeByte((if (mutable) Opcode.DECLARE_VARIABLE_MUTABLE else Opcode.DECLARE_VARIABLE_IMMUTABLE).ordinal)
            .writeU24(nameConst.requireU24("DeclareVariableInsn#nameConst"))
    }
}