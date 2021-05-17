package sample;


public class Tool {
    public static boolean isLetter(char c)
    {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static boolean isDigit(char c)
    {
        return (c >= '0' && c <= '9');
    }

    public static boolean toStartFromError(char c){
        return c == ' ' || c== '{' || c == ':' || c == '+' || c == '-' || c=='*' || c=='/' || c=='=' || c=='>'|| c=='<' || c=='(' || c==')' ||c==';';
    }

}
