package net.notjustanna.lin.vm

import net.notjustanna.lin.exception.LinNullPointerException
import net.notjustanna.lin.exception.LinUnsupportedOperationException
import net.notjustanna.lin.vm.types.*

public object LinRuntime {
    public val ensureNotNull: LNativeFunction = LNativeFunction("ensureNotNull") { _, args ->
        if (args.any { it == LNull }) {
            throw LinNullPointerException()
        }
        LTrue
    }

    public val iterator: LNativeFunction = LNativeFunction("iterator") { thisValue, _ ->
        val it = when (thisValue) {
            is LArray -> {
                thisValue.value.iterator()
            }
            is LObject -> {
                thisValue.value.asSequence().map(LAny::ofEntry).iterator()
            }
            is LRange -> {
                thisValue.value.asSequence().map(::LInteger).iterator()
            }
            null -> {
                throw LinNullPointerException()
            }
            else -> {
                throw LinUnsupportedOperationException("iterator", thisValue.linType)
            }
        }
        LObject.of(
            LString("__hasNext") to LNativeFunction("hasNext") { _, _ -> LAny.of(it.hasNext()) },
            LString("__next") to LNativeFunction("next") { _, _ -> it.next() },
        )
    }
}
