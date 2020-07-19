package io.github.cafeteriaguild.lin.parser.parselets.special

import net.notjustanna.tartar.api.parser.ParserContext
import net.notjustanna.tartar.api.parser.PrefixParser
import net.notjustanna.tartar.api.parser.SyntaxException
import net.notjustanna.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.ast.expr.Expr
import io.github.cafeteriaguild.lin.ast.expr.Node
import io.github.cafeteriaguild.lin.ast.expr.misc.InvalidNode
import io.github.cafeteriaguild.lin.ast.expr.misc.UnitExpr
import io.github.cafeteriaguild.lin.ast.expr.nodes.ReturnExpr
import io.github.cafeteriaguild.lin.lexer.TokenType

object ReturnParser : PrefixParser<TokenType, Node> {
    override fun parse(ctx: ParserContext<TokenType, Node>, token: Token<TokenType>): Node {
        val node = if (ctx.matchAny(TokenType.NL, TokenType.SEMICOLON)) {
            UnitExpr(token.section)
        } else {
            ctx.parseExpression().let {
                it as? Expr ?: return InvalidNode {
                    section(token.section)
                    child(it)
                    error(SyntaxException("Expected an expression", it.section))
                }
            }
        }

        return ReturnExpr(node, token.section)
    }
}