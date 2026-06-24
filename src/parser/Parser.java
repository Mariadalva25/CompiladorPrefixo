package parser;

import java.util.List;
import lexer.Token;
import lexer.TokenType;

public class Parser {

    private List<Token> tokens;
    private int pos = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    private Token lookahead() {
        return tokens.get(pos);
    }

    private void match(TokenType expected) {

        if (lookahead().getType() == expected) {
            pos++;
        } else {
            throw new RuntimeException(
                    "Erro sintático: esperado "
                    + expected
                    + ", encontrado "
                    + lookahead().getLexeme());
        }
    }

    public String parse() {

        String result = expr();

        match(TokenType.EOF);

        return result;
    }

    private String expr() {

        String left = term();

        while (lookahead().getType() == TokenType.PLUS
                || lookahead().getType() == TokenType.MINUS) {

            Token op = lookahead();

            if (op.getType() == TokenType.PLUS) {
                match(TokenType.PLUS);
            } else {
                match(TokenType.MINUS);
            }

            String right = term();

            left = op.getLexeme()
                    + " "
                    + left
                    + " "
                    + right;
        }

        return left;
    }

    private String term() {

        String left = power();

        while (lookahead().getType() == TokenType.MULT
                || lookahead().getType() == TokenType.DIV
                || lookahead().getType() == TokenType.MOD) {

            Token op = lookahead();

            switch (op.getType()) {

                case MULT:
                    match(TokenType.MULT);
                    break;

                case DIV:
                    match(TokenType.DIV);
                    break;

                case MOD:
                    match(TokenType.MOD);
                    break;
            }

            String right = power();

            left = op.getLexeme()
                    + " "
                    + left
                    + " "
                    + right;
        }

        return left;
    }

    private String power() {

        String left = unary();

        if (lookahead().getType() == TokenType.POW) {

            Token op = lookahead();

            match(TokenType.POW);

            String right = power();

            return op.getLexeme()
                    + " "
                    + left
                    + " "
                    + right;
        }

        return left;
    }

    private String unary() {

        if (lookahead().getType() == TokenType.PLUS) {

            match(TokenType.PLUS);

            String operand = unary();

            return "+u " + operand;
        }

        if (lookahead().getType() == TokenType.MINUS) {

            match(TokenType.MINUS);

            String operand = unary();

            return "-u " + operand;
        }

        return primary();
    }

    private String primary() {

        Token token = lookahead();

        if (token.getType() == TokenType.ID) {

            match(TokenType.ID);
            return token.getLexeme();
        }

        if (token.getType() == TokenType.NUMBER) {

            match(TokenType.NUMBER);
            return token.getLexeme();
        }

        if (token.getType() == TokenType.LPAREN) {

            match(TokenType.LPAREN);

            String expr = expr();

            match(TokenType.RPAREN);

            return expr;
        }

        throw new RuntimeException(
                "Erro sintático próximo ao token: "
                + token.getLexeme());
    }
}
