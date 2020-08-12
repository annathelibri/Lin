package io.github.cafeteriaguild.lin.rt.lib

import io.github.cafeteriaguild.lin.rt.exceptions.LinException
import io.github.cafeteriaguild.lin.rt.lib.nativelang.properties.ObjProperty
import io.github.cafeteriaguild.lin.rt.lib.nativelang.properties.Property

interface LObj {
    fun properties(): Set<String> {
        return emptySet()
    }

    fun canGet(name: String): Boolean {
        return false
    }

    operator fun get(name: String): LObj {
        throw UnsupportedOperationException()
    }

    fun canSet(name: String): Boolean {
        return false
    }

    operator fun set(name: String, value: LObj) {
        throw UnsupportedOperationException()
    }

    /**
     * Overload this function if you're a lambda or function.
     */
    fun canInvoke(): Boolean {
        return canGet("invoke") && get("invoke").canInvoke()
    }

    /**
     * Overload this function if you're a lambda or function.
     */
    operator fun invoke(args: List<LObj> = emptyList()): LObj {
        if (!canGet("invoke")) throw LinException("Object does not support invocation.")
        return get("invoke").invoke(args)
    }

    fun propertyOf(name: String): Property? = if (properties().contains(name)) ObjProperty(this, name) else null

    fun component(value: Int): LObj? {
        val property = propertyOf("component$value") ?: return null
        val componentFn = property.get()
        if (!componentFn.canInvoke()) return null
        return componentFn.invoke()
    }
}
