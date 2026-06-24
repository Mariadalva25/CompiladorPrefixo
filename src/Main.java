import lexer.Lexer;
import lexer.Token;
import parser.Parser;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite uma expressão:");

        String entrada = sc.nextLine();

        Lexer lexer = new Lexer(entrada);

        List<Token> tokens = lexer.tokenize();

        System.out.println("\nTOKENS:");

        for (Token t : tokens) {
            System.out.println(t);
        }

        Parser parser = new Parser(tokens);

        String prefixa = parser.parse();

        System.out.println("\nExpressão Prefixa:");

        System.out.println(prefixa);
    }
}