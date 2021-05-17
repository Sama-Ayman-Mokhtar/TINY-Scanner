package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import java.util.Map;

public class Main extends Application {
    private BorderPane bp;
    private GridPane rightGP = new GridPane();
    private GridPane bottomGP = new GridPane();
    private ScrollPane sp = getSp();
    private ScrollPane spBottom = getSpBottom();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        bp = new BorderPane();

        HBox topi = new HBox();
        Label inst = new Label("Please, Delete The TINY Code Below, Paste Yours, Then click Continue");
        inst.setStyle("-fx-font-size: 16pt;");
        topi.getChildren().addAll(inst);
        topi.setSpacing(20);
        topi.setAlignment(Pos.CENTER);
        topi.setPadding(new Insets(20,20,20,20));
        bp.setTop(topi);

        VBox bv = new VBox();
        Button cont = new Button("Continue");
        cont.setStyle("-fx-font-size: 16pt");
        bv.getChildren().add(cont);
        bv.setAlignment(Pos.CENTER);
        bv.setPadding(new Insets(20,20,20,20));
        //cont.setOnAction(e -> nextAction());
        bp.setBottom(bv);

        TextArea ta = new TextArea();
        ta.setText("{ Sample program in TINY-language computes factorial }\n" +
                "read x; { input an integer }\n" +
                "if 0 < x then { don't compute if x <= 0 }\n" +
                "    fact := 1;\n" +
                "    repeat\n" +
                "        fact := fact * x;\n" +
                "        x := x - 1\n" +
                "    until x = 0;\n" +
                "    write fact { output factorial of x }\n" +
                "end");
        //ta.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
        ta.setFont(Font.font(16));
      //  ta.setPadding(new Insets(50));
        bp.setCenter(ta);
        cont.setOnAction(e-> {
            FileWriter.writeFile(ta.getText());
            FileReader.openFile();
            FileReader.getTokens();
           // FileReader.printTokens();
            FileReader.closeFile();
            fillRightGP();
            fillTop();
            fillBottomGP();
           // bp.setRight();
            bp.setRight(sp);
            bp.setBottom(spBottom);
            /*addRowMiddleGP(0,0);
            middleGP.setVgap(10);
            middleGP.setHgap(10);
            middleGP.setPadding(new Insets(20,20,20,20));
            //middleGP.setGridLinesVisible(true);
            getLeftVBox();
            bp.setRight(leftVBox);
            bp.setCenter(sp);
            bp.setBottom(getBottomVBox("Next Instruction"));
            bp.setTop(getTopHBox("Click 'Next Instruction' to Start"));
            Controller.setlineCount();*/
            //System.out.println(Controller.InstHashMap);
            // Register.s0.setValue(Tool.decimaltoTwosComplement("10"));
            //Register.s1.setValue(Tool.decimaltoTwosComplement("12"));
            // Register.s2.setValue(Tool.decimaltoTwosComplement("32"));
            //Register.s5.setValue(Tool.decimaltoTwosComplement("500"));

        });
        Scene scene = new Scene(bp, 900, 600);
        primaryStage.setTitle("Scanner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private ScrollPane getSp(){
        ScrollPane sp = new ScrollPane();
        sp.setContent(rightGP);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        sp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        //sp.setFitToWidth(true);
        sp.setPannable(true);
        return  sp;
    }
    private ScrollPane getSpBottom(){
        ScrollPane sp = new ScrollPane();
        sp.setContent(bottomGP);
        //sp.setPrefSize(1920, 1080);
       // sp.setPrefViewportWidth(100);
        sp.setPrefViewportHeight(150);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        sp.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        //sp.setFitToWidth(true);
        sp.setPannable(true);
        return  sp;
    }
    private void fillTop(){
        HBox topi = new HBox();
        Label inst = new Label("Scanner: Tokens and Token Type of Input Code");
        inst.setStyle("-fx-font-size: 16pt;");
        topi.getChildren().addAll(inst);
        topi.setSpacing(20);
        topi.setAlignment(Pos.CENTER);
        topi.setPadding(new Insets(20,20,20,20));
        bp.setTop(topi);
    }
    private void fillRightGP(){
        rightGP.setVgap(5);
        rightGP.setHgap(30);
        rightGP.setPadding(new Insets(10,40,40,40));
        Label h1 = new Label("Token");
        h1.setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;");
        Label h2 = new Label("Token Type");
        h2.setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;");
        rightGP.add(h1,0,0);
        rightGP.add(h2,1,0);
        for (int i = 0; i < FileReader.Tokens.size(); i++) {
            Label temp = new Label(FileReader.Tokens.get(i).tokenType.name);
           temp.setStyle("-fx-font-size: 12pt;");
            rightGP.add(temp,1,i+1);

            if (FileReader.Tokens.get(i).tokenType == TokenType.identifier){
                temp = new Label(FileReader.Tokens.get(i).strVal);
                temp.setStyle("-fx-font-size: 12pt;");
                rightGP.add(temp,0,i+1);
            }
            else if (FileReader.Tokens.get(i).tokenType == TokenType.number) {
                temp = new Label(String.valueOf(FileReader.Tokens.get(i).numVal));
                temp.setStyle("-fx-font-size: 12pt;");
                rightGP.add(temp, 0, i + 1);
            }
            else {
                temp = new Label(String.valueOf(FileReader.Tokens.get(i).tokenType.val));
                temp.setStyle("-fx-font-size: 12pt;");
                rightGP.add(temp, 0, i + 1);
            }
        }

    }
    private void fillBottomGP(){
        bottomGP.setVgap(5);
        bottomGP.setHgap(30);
        bottomGP.setPadding(new Insets(10,40,40,40));
        Label h1 = new Label("Line #");
        h1.setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;");
        Label h2 = new Label("Error: Unidentified Token");
        h2.setStyle("-fx-font-weight: bold;-fx-font-size: 12pt;");
        bottomGP.add(h1,0,0);
        bottomGP.add(h2,1,0);
        for (int i = 0; i < FileReader.unrecognizedTokens.size(); i++) {
            Label temp = new Label(String.valueOf(FileReader.unrecognizedTokens.get(i).lineNum));
            temp.setStyle("-fx-font-size: 12pt;");
            bottomGP.add(temp,0,i+1);
            temp = new Label(String.valueOf(FileReader.unrecognizedTokens.get(i).val));
            temp.setStyle("-fx-font-size: 12pt;");
            bottomGP.add(temp,1,i+1);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
