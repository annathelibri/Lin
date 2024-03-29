package net.notjustanna.lin.vm.types

import net.notjustanna.lin.vm.LinRuntime

public data class LRange(val value: LongRange) : LAny() {
    override fun truth(): Boolean {
        return true
    }

    override val linType: String
        get() = "range"

    override fun getMember(name: String): LAny? {
        if (name == "__iterator") {
            return LinRuntime.iterator
        }
        return null
    }
}
