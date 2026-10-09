import java.util.List;

public class Main {
    public static void main(String[] args) {
        lexer lexerInstance = new lexer();

        // Define your test input string
        String testInput = "def Head (C (x,xs)) = x";

        // Pass the string into the lex(s) method
        List<Token> tokens = lexerInstance.lex(testInput);

        // Print the resulting list of tokens
        System.out.println("Tokens: " + tokens);
    }
}