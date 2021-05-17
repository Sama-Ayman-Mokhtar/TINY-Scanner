package sample;

public class Token {
    TokenType tokenType;
    String strVal;
    Integer numVal;

    Token(TokenType tt){
        tokenType = tt;
    }

    Token(TokenType tt, String str) {
        tokenType = tt;
        strVal = str;
    }

    Token(TokenType tt, Integer num) {
        tokenType = tt;
        numVal = num;
    }
}
