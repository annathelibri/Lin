package net.notjustanna.lin.parser.parselets.misc

import net.notjustanna.lin.ast.node.Expr
import net.notjustanna.lin.ast.node.InvalidNode
import net.notjustanna.lin.ast.node.Node
import net.notjustanna.lin.ast.node.access.SubscriptAccessExpr
import net.notjustanna.lin.ast.node.access.SubscriptAssignNode
import net.notjustanna.lin.lexer.TokenType
import net.notjustanna.lin.parser.Precedence
import net.notjustanna.lin.parser.utils.maybeIgnoreNL
import net.notjustanna.tartar.api.grammar.InfixParselet
import net.notjustanna.tartar.api.parser.ParserContext
import net.notjustanna.tartar.api.parser.SyntaxException
import net.notjustanna.tartar.api.parser.Token

public object SubscriptParser : InfixParselet<TokenType, Token<TokenType>, Node> {
    override val precedence: Int = Precedence.POSTFIX

    override fun parse(
        ctx: ParserContext<TokenType, Token<TokenType>, Node>,
        left: Node,
        token: Token<TokenType>
    ): Node {
        if (left !is Expr) {
            return InvalidNode {
                section(token.section)
                child(left)
                error(SyntaxException("Expected an expression", left.section))
            }
        }
        val arguments = mutableListOf<Expr>()

        if (!ctx.match(TokenType.R_BRACKET)) {
            do {
                //TODO Implement Spread Operator
                arguments += ctx.parseExpression().let {
                    it as? Expr ?: return InvalidNode {
                        section(token.section)
                        child(it)
                        error(SyntaxException("Expected an expression", it.section))
                    }
                }
            } while (ctx.match(TokenType.COMMA))
            ctx.eat(TokenType.R_BRACKET)
        }

        val rBracket = ctx.last

        return if (ctx.match(TokenType.ASSIGN)) {
            val value = ctx.parseExpression().let {
                it as? Expr ?: return InvalidNode {
                    section(token.section)
                    child(it)
                    error(SyntaxException("Expected an expression", it.section))
                }
            }
            ctx.maybeIgnoreNL()
            SubscriptAssignNode(left, arguments, value, left.span(value))
        } else {
            ctx.maybeIgnoreNL()
            SubscriptAccessExpr(left, arguments, left.span(rBracket))
        }
    }
}
