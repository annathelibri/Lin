package net.notjustanna.lin.bytecode.insn

import net.notjustanna.lin.bytecode.utils.requireU24
import net.notjustanna.lin.bytecode.utils.writeU24
import okio.Buffer

public data class BranchIfInsn(val value: Boolean, val labelCode: Int) : Insn() {
    override fun serializeTo(buffer: Buffer) {
        buffer.writeByte((if (value) Opcode.BRANCH_IF_TRUE else Opcode.BRANCH_IF_FALSE).ordinal)
            .writeU24(labelCode.requireU24("BranchIfInsn#labelCode"))
    }
}
