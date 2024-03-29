package net.notjustanna.lin.lexer

import net.notjustanna.lin.lexer.TokenType.*
import net.notjustanna.tartar.api.dsl.CharPredicate
import net.notjustanna.tartar.api.lexer.Lexer
import net.notjustanna.tartar.api.parser.Token
import net.notjustanna.tartar.extensions.lexer.*

public typealias LinToken = Token<TokenType>

internal fun linStdLexer() = Lexer.create<LinToken> {
    ' ' { matchSpaceAndNL() }
    '\r' {
        match('\n')
        processToken(NL)
        matchSpaceAndNL()
    }
    '\n' {
        processToken(NL)
        matchSpaceAndNL()
    }
    '{' { processToken(L_BRACE) }
    '}' { processToken(R_BRACE) }
    '(' { processToken(L_PAREN) }
    ')' { processToken(R_PAREN) }
    '[' { processToken(L_BRACKET) }
    ']' { processToken(R_BRACKET) }
    ".." { processToken(RANGE, 2) }
    '.' { processToken(DOT) }
    ',' { processToken(COMMA) }
    "::" { processToken(DOUBLE_COLON, 2) }
    ':' { processToken(COLON) }
    ';' { processToken(SEMICOLON) }
    '\u037E' { processToken(SEMICOLON) } // Greek question mark
    "+=" { processToken(PLUS_ASSIGN, 2) }
    "++" { processToken(INCREMENT, 2) }
    '+' { processToken(PLUS) }
    "->" { processToken(ARROW, 2) }
    "-=" { processToken(MINUS_ASSIGN, 2) }
    "--" { processToken(DECREMENT, 2) }
    '-' { processToken(MINUS) }
    "%=" { processToken(REM_ASSIGN, 2) }
    '%' { processToken(REM) }
    "*=" { processToken(ASTERISK_ASSIGN, 2) }
    '*' { processToken(ASTERISK) }
    "//" { while (hasNext()) if (next() == '\n') break }
    "/*" { while (hasNext()) if (next() == '*' && match('/')) break }
    "/=" { processToken(SLASH_ASSIGN, 2) }
    '/' { processToken(SLASH) }
    '\\' { processToken(BACKSLASH) }
    "!=" { processToken(NEQ, 2) }
    "!!" { processToken(DOUBLE_BANG, 2) }
    '!' { processToken(BANG) }
    "?:" { processToken(ELVIS) }
    "?." { processToken(QUESTION_DOT, 2) }
    '?' { processToken(QUESTION) }
    "==" { processToken(EQ, 2) }
    '=' { processToken(ASSIGN) }
    "||" { processToken(OR, 2) }
    "|" { processToken(PIPE) }
    "&&" { processToken(AND, 2) }
    "&" { processToken(AMP) }
    "<=" { processToken(LTE, 2) }
    '<' { processToken(LT) }
    ">=" { processToken(GTE, 2) }
    '>' { processToken(GT) }
    '\'' { readLinTemplateString("'", false) }
    "\"\"\"" { readLinTemplateString("\"\"\"", true) }
    "\"\"" { processToken(STRING, "", 2) }
    '"' { readLinTemplateString("\"", false) }
    "`" { processToken(IDENTIFIER, readString(it), offset = 2) }
    matching(CharPredicate.isDigit).configure {
        process(
            when (val n = readNumber(it)) {
                is LexicalNumber.Invalid -> token(INVALID, n.string)
                is LexicalNumber.Decimal -> DoubleToken(DECIMAL, n.value, section(n.string.length))
                is LexicalNumber.Integer -> LongToken(INTEGER, n.value, section(n.string.length))
            }
        )
    }
    matching { it.isLetter() || it == '_' || it == '@' }.configure {
        when (val s = readLinIdentifier(it)) {
            "break" -> processToken(BREAK, 5)
            "continue" -> processToken(CONTINUE, 8)
            "do" -> processToken(DO, 2)
            "else" -> processToken(ELSE, 4)
            "false" -> processToken(FALSE, 4)
            "for" -> processToken(FOR, 3)
            "fun" -> processToken(FUN, 3)
            "if" -> processToken(IF, 2)
            "in" -> processToken(IN, 2)
            "is" -> processToken(IS, 2)
            "null" -> processToken(NULL, 4)
            "return" -> processToken(RETURN, 6)
            "this" -> processToken(THIS, 4)
            "throw" -> processToken(THROW, 5)
            "true" -> processToken(TRUE, 4)
            "try" -> processToken(TRY, 3)
            "typeof" -> processToken(TYPEOF, 6)
            "val" -> processToken(VAL, 3)
            "var" -> processToken(VAR, 3)
            "when" -> processToken(WHEN, 4)
            "while" -> processToken(WHILE, 5)

            else -> processToken(IDENTIFIER, s)
        }
    }
    configure { processToken(INVALID, next().toString()) }
}
