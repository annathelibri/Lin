package net.notjustanna.lin.bytecode

import net.notjustanna.lin.bytecode.insn.Insn
import net.notjustanna.lin.utils.Deserializer
import net.notjustanna.lin.utils.Serializable
import okio.Buffer

data class CompiledNode(
    val instructions: List<Insn>,
    val jumpLabels: List<JumpLabel>,
    val sectionLabels: List<SectionLabel>
) : Serializable {
    override fun serializeTo(buffer: Buffer) {
        buffer.writeInt(instructions.size)
        for (insn in instructions) insn.serializeTo(buffer)

        buffer.writeInt(jumpLabels.size)
        for (label in jumpLabels.sortedBy { it.code }) label.serializeTo(buffer)

        buffer.writeInt(sectionLabels.size)
        for (label in sectionLabels) label.serializeTo(buffer)
    }

    fun resolveLabel(code: Int): Int {
        val indexOf = jumpLabels.binarySearchBy(code) { it.code }
        check(indexOf >= 0) { "Label $code was not found." }
        return jumpLabels[indexOf].at
    }

    companion object : Deserializer<CompiledNode> {
        override fun deserializeFrom(buffer: Buffer): CompiledNode {
            val instructions = mutableListOf<Insn>()
            repeat(buffer.readInt()) {
                instructions += Insn.deserializeFrom(buffer)
            }

            val jumpLabels = mutableListOf<JumpLabel>()
            repeat(buffer.readInt()) {
                jumpLabels += JumpLabel.deserializeFrom(buffer)
            }

            val sectionLabels = mutableListOf<SectionLabel>()
            repeat(buffer.readInt()) {
                sectionLabels += SectionLabel.deserializeFrom(buffer)
            }

            return CompiledNode(instructions, jumpLabels, sectionLabels)
        }
    }
}
