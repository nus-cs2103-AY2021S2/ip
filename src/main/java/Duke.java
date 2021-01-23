import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {



    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();
        ui.greeting();
        if (!Files.exists(Paths.get("data"))) {
            Files.createDirectory(Paths.get("data"));
        }
        else if (!Files.exists(Paths.get("data/Duke.txt"))) {
            Files.createFile(Paths.get("data/Duke.txt"));
        }

        Parser parser = new Parser();
        parser.runParsing();


    }
}
