package net.notjustanna.lin.bytecode.insn

import net.notjustanna.lin.bytecode.utils.writeU24
import okio.Buffer

object UnaryNegativeOperationInsn : Insn() {
    override fun serializeTo(buffer: Buffer) {
        buffer.writeByte(Opcode.PARAMETERLESS.ordinal).writeU24(ParameterlessCode.UNARY_NEGATIVE.ordinal)
    }
}
