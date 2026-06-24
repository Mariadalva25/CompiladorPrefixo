package lexer;

import java.util.ArrayList;
import java.util.List;
public class Lexer {

    private String input;
    private int pos;

    public Lexer(String input) {
        this.input = input;
        this.pos = 0;
    }

    public List<Token> tokenize() {

        List<Token> tokens = new ArrayList<>();

        while (pos < input.length()) {

            char c = input.charAt(pos);

            if (Character.isWhitespace(c)) {
                pos++;
                continue;
            }

            if (Character.isLetter(c)) {

                StringBuilder sb = new StringBuilder();

                while (pos < input.length() &&
                        Character.isLetterOrDigit(input.charAt(pos))) {

                    sb.append(input.charAt(pos));
                    pos++;
                }

                tokens.add(new Token(TokenType.ID, sb.toString()));
                continue;
            }

            if (Character.isDigit(c)) {

                StringBuilder sb = new StringBuilder();

                while (pos < input.length() &&
                        (Character.isDigit(input.charAt(pos))
                                || input.charAt(pos) == '.')) {

                    sb.append(input.charAt(pos));
                    pos++;
                }

                tokens.add(new Token(TokenType.NUMBER, sb.toString()));
                continue;
            }

            switch (c) {

                case '+':
                    tokens.add(new Token(TokenType.PLUS, "+"));
                    break;

                case '-':
                    tokens.add(new Token(TokenType.MINUS, "-"));
                    break;

                case '*':
                    tokens.add(new Token(TokenType.MULT, "*"));
                    break;

                case '/':
                    tokens.add(new Token(TokenType.DIV, "/"));
                    break;

                case '%':
                    tokens.add(new Token(TokenType.MOD, "%"));
                    break;

                case '^':
                    tokens.add(new Token(TokenType.POW, "^"));
                    break;

                case '(':
                    tokens.add(new Token(TokenType.LPAREN, "("));
                    break;

                case ')':
                    tokens.add(new Token(TokenType.RPAREN, ")"));
                    break;

                default:
                    throw new RuntimeException(
                            "Caractere inválido: " + c);
            }

            pos++;
        }

        tokens.add(new Token(TokenType.EOF, "EOF"));

        return tokens;
    }
}