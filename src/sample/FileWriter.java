package sample;
import java.io.File;
import java.io.PrintWriter;
public class FileWriter {

    static PrintWriter write ;
    static void writeFile(String s) {
        try {
            File file = new File("input");
            write = new PrintWriter(file);

        } catch (Exception e) {
            System.out.println("File not Found! {while writing}");
        }
        //write.printf(s); % result in problems
        write.print(s);
        write.close();
    }
}
