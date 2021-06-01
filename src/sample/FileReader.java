package sample;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class FileReader {
    static Scanner scan;
    public static ArrayList<Token> Tokens = new ArrayList<Token>();
    public static ArrayList<ErrorElement> unrecognizedTokens = new ArrayList<ErrorElement>();
    static  void openFile(){
        try{
            File file = new File("input");
            scan = new Scanner(file);
        }
        catch (Exception e){
            System.out.println("File not Found!");
        }
    }
    static void printTokens(){
        for (Token tt: FileReader.Tokens) {
            System.out.print(tt.tokenType.name + " ");
            if (tt.tokenType == TokenType.identifier)
                System.out.print(tt.strVal);
            else if (tt.tokenType == TokenType.number)
                System.out.print(tt.numVal);
            else
                System.out.print(tt.tokenType.val);
            System.out.println();
        }
    }
    static void printUrecogTokens(){
        for (ErrorElement tt: FileReader.unrecognizedTokens) {
            System.out.print(tt.lineNum + " " + tt.val);
            System.out.println();
        }
    }
    static void getTokens(){
        Tokens = new ArrayList<Token>();
        unrecognizedTokens = new ArrayList<ErrorElement>();
        int lineCount = 0;
        while(scan.hasNext()){
            lineCount++;
            State currentState = State.start;
            StringBuilder str = new StringBuilder();
            StringBuilder strError = new StringBuilder();
            String line = scan.nextLine();
            line = line.concat(" ");
            for (int i = 0; i < line.length(); i++) {
                char input = line.charAt(i);
                if (currentState == State.start){
                    if (input == '+')
                        Tokens.add(new Token(TokenType.plus));
                    else if (input == '-')
                        Tokens.add(new Token(TokenType.minus));
                    else if (input == '*')
                        Tokens.add(new Token(TokenType.multiplicaion));
                    else if (input == '/')
                        Tokens.add(new Token(TokenType.division));
                    else if (input == '=')
                        Tokens.add(new Token(TokenType.comparison));
                    else if (input == '<')
                        Tokens.add(new Token(TokenType.lessThan));
                    else if (input == '>')
                        Tokens.add(new Token(TokenType.greaterThan));
                    else if (input == '(')
                        Tokens.add(new Token(TokenType.openBracket));
                    else if (input == ')')
                        Tokens.add(new Token(TokenType.closeBracket));
                    else if (input == ';')
                        Tokens.add(new Token(TokenType.semicolon));
                    else if (Tool.isDigit(input)) {
                        currentState = State.inNum;
                        str.append(input);
                    }
                    else if (Tool.isLetter(input)) {
                        currentState = State.inID;
                        str.append(input);
                    }
                    else if (input == ':') {
                        currentState = State.inAssign;
                    }
                    else if (input == '{') {
                        currentState = State.inComment;
                    }
                    else if (input != ' ') {
                        currentState = State.inError;
                        strError.append(input);
                    }
                }
                else if (currentState == State.inError){
                    if(Tool.toStartFromError(input)){
                        i--;
                        currentState = State.start;
                        unrecognizedTokens.add(new ErrorElement(lineCount, String.valueOf(strError)));
                        strError.setLength(0);
                    }
                    else
                        strError.append(input);
                }
                else if (currentState == State.inComment) {
                    if (input == '}')
                        currentState = State.start;
                }
                else if (currentState == State.inNum) {
                    if (Tool.isDigit(input))
                        str.append(input);
                    else {
                        Tokens.add(new Token(TokenType.number, Integer.valueOf(String.valueOf(str))));
                        str.setLength(0);
                        currentState = State.start;
                        i--;
                    }
                }
                else if (currentState == State.inID) {
                    if (Tool.isLetter(input))
                        str.append(input);
                    else {
                        Tokens.add(new Token(TokenType.identifier, String.valueOf(str)));
                        str.setLength(0);
                        currentState = State.start;
                        i--;
                    }
                }
                else if (currentState == State.inAssign) {
                    if (input == '=') {
                        Tokens.add(new Token(TokenType.assignSymbol));
                        currentState = State.start;
                    }
                    else {
                        currentState = State.inError;
                        strError.append(':');
                        i--;
                    }
                }
            }
        }
        setReservedWords();
    }
    static void setReservedWords(){
        for (Token T: Tokens) {
            if (T.tokenType == TokenType.identifier){
                for (TokenType tt: TokenType.values()) {
                    if (tt != TokenType.identifier && tt != TokenType.number) {
                        if (tt.val.equals(T.strVal)){
                            T.tokenType = tt;
                        }
                    }
                }
            }
        }
    }
    static void closeFile(){
        scan.close();
    }
}
class ErrorElement{
    int lineNum;
    String val;

    ErrorElement(int lineNum, String val){
        this.lineNum = lineNum;
        this.val = val;
    }
}