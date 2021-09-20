package net.notjustanna.lin.parser.parselets.control

import net.notjustanna.lin.ast.Expr
import net.notjustanna.lin.ast.InvalidNode
import net.notjustanna.lin.ast.Node
import net.notjustanna.lin.ast.WhileNode
import net.notjustanna.lin.lexer.TokenType
import net.notjustanna.lin.parser.utils.parseBlock
import net.notjustanna.lin.parser.utils.skipOnlyUntil
import net.notjustanna.tartar.api.parser.ParserContext
import net.notjustanna.tartar.api.parser.PrefixParser
import net.notjustanna.tartar.api.parser.SyntaxException
import net.notjustanna.tartar.api.parser.Token
import io.github.cafeteriaguild.lin.parser.utils.matchAll

object WhileParser : PrefixParser<TokenType, Node> {
    override fun parse(ctx: ParserContext<TokenType, Node>, token: Token<TokenType>): Node {
        ctx.matchAll(TokenType.NL)
        ctx.eat(TokenType.L_PAREN)
        ctx.matchAll(TokenType.NL)
        val condition = ctx.parseExpression().let {
            it as? Expr ?: return InvalidNode {
                section(token.section)
                child(it)
                error(SyntaxException("Expected an expression", it.section))
            }
        }

        ctx.matchAll(TokenType.NL)
        ctx.eat(TokenType.R_PAREN)

        ctx.skipOnlyUntil()

        ctx.matchAll(TokenType.NL)
        val expr = if (ctx.match(TokenType.SEMICOLON)) null else ctx.parseBlock() ?: ctx.parseExpression()
        return WhileNode(condition, expr, token.section)
    }
}
