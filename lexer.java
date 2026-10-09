import java.util.ArrayList;
import java.util.List;

public final class lexer {
    private int idx = 0;
    private String input;
    private final List<Token> output = new ArrayList<>();


    private Character peek(){
        if(idx >= this.input.length()){
            return '$';
        }
        else{
            return this.input.charAt(idx);
        }
    }

    private void drop(){
        idx = idx +1;
    }

    private void emit(Token tk){
        output.add(tk);
    }

    private boolean LowerUpper(char c){
        if(c >= 'A' && c <= 'Z'){
            return true;
        } else if(c >= 'a' && c <= 'z'){
            return true;
        }
        else {return false;}
    }

    private void lex_var_or_id_or_kw(String lexeme){
        char c = peek();
        if(LowerUpper(c)) {
            lexeme = lexeme + c;
            drop();
            lex_var_or_id_or_kw(lexeme);
        } else {
            if (lexeme.equals("def")) {
                emit(Token.DEFINE);
                lexing();
            } else if (lexeme.charAt(0) >= 'a' && lexeme.charAt(0) <= 'z') {
                emit(Token.VAR);
                lexing();
            }else {
                emit(Token.IDENT);
                lexing();
            }
        }

    }

    private void lexing(){
        if(peek() == '$'){
            emit(Token.END);
            return;
        }
        else {
            char c = peek();
            if(c == '('){
                drop();
                emit(Token.LPAREN);
                lexing();
            }
            if(c == ')'){
                drop();
                emit(Token.RPAREN);
                lexing();
            }
            if(c == '='){
                drop();
                emit(Token.EQUAL);
                lexing();
            }
            if(c == ','){
                drop();
                emit(Token.COMMA);
                lexing();
            }
            if(c == ' '){
                drop();
                lexing();
            }
            if(c == '\n'){
                drop();
                lexing();
            }
            if(c == '\r'){
                drop();
                lexing();
            }
            if(LowerUpper(c)){
                String lexeme = "";
                lex_var_or_id_or_kw(lexeme);
            }
            else {
                return;
            }
        }
    }

    public List<Token> lex(String s) {
        this.input = s;
        this.idx = 0;
        this.output.clear();
        lexing();
        return this.output;
    }
}