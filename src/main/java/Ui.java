import java.util.List;
import java.util.Scanner;
import Exception.DukeException;

public class Ui {
    private Scanner sc;
    private Parser parser;

    public Ui(Scanner sc, Parser parser) {
        printStatements();
        this.sc = sc;
        this.parser = parser;
    }

    /**
     * Allows user to enter string text into the command line
     */
    public void executeInput() {
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            try {
                parser.checkInput(userInput);
                boolean check = parser.determineAction(userInput);
                if (!check) {
                    break;
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void printStatements() {
        String line = "";
        for (int i = 0; i < 50; i++) {
            line = line.concat("_");
        }
        System.out.println(line);
        System.out.println("Hello! I'm Benny");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }


}
