package net.notjustanna.lin.parser.utils

import net.notjustanna.tartar.api.parser.ParserContext
import net.notjustanna.tartar.api.parser.Token

internal fun <T, E> ParserContext<T, Token<T>, E>.matchAll(vararg types: T): Boolean {
    return if (nextIsAny(*types)) {
        do eat() while (nextIsAny(*types))
        true
    } else {
        false
    }
}
