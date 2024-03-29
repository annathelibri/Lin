package net.notjustanna.lin.parser.parselets.control

import net.notjustanna.lin.ast.node.Node
import net.notjustanna.lin.ast.node.control.ContinueExpr
import net.notjustanna.lin.lexer.TokenType
import net.notjustanna.tartar.api.grammar.PrefixParselet
import net.notjustanna.tartar.api.parser.ParserContext
import net.notjustanna.tartar.api.parser.Token

public object ContinueParser : PrefixParselet<TokenType, Token<TokenType>, Node> {
    override fun parse(ctx: ParserContext<TokenType, Token<TokenType>, Node>, token: Token<TokenType>): Node {
        return ContinueExpr(token.section)
    }
}
