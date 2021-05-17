package sample;

public enum TokenType {
    identifier("Identifier"), number("Number"),
    assignSymbol("Assignment Symbol", ":="), plus("Plus Symbol", "+"), semicolon("Semicolon Symbol", ";"),
    minus("Minus Symbol", "-"), multiplicaion("Multiplicaion Symbol", "*"),
    division("Division Symbol", "/"), comparison("Comparison Symbol", "="),
    lessThan("Less Than Symbol", "<"), greaterThan("Greater Than Symbol", ">"),
    openBracket("Open Bracket Symbol", "("), closeBracket("close Bracket Symbol", ")"),
    iif("If (Reserved Word)", "if"), then("Then (Reserved Word)", "then"),
    eelse("Else (Reserved Word)", "else"), end("End (Reserved Word)", "end"),
    repeat("Repeat (Reserved Word)", "repeat"), until("Until (Reserved Word)", "until"),
    read("Read (Reserved Word)", "read"), write("Write (Reserved Word)", "write");

    String name;
    String val;
    private TokenType(String name){
        this.name = name;
    }
    private TokenType(String name, String val){
        this.name = name;
        this.val = val;
    }
}

