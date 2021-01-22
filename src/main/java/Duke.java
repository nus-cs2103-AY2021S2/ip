import java.util.Scanner;
import java.util.ArrayList;

/**
 * Duke is a project that eventually builds into a personal assistant chat bot.
 */
public class Duke {

    //tracks all tasks
    public static ArrayList<Task> tasks = Storage.loadData();

    /**
     * Entry point of the program, first greets then listens for input from user.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Parser parser = new Parser();
        Ui.showWelcome();
        listenInput(parser);
    }

    /**
     * Listens for input from the user.
     * @param parser command class that handles parsing of input
     */
    public static void listenInput(Parser parser) {
        Scanner sc = new Scanner(System.in);
        Ui.showDivider();

        while (sc.hasNextLine()) {
            Ui.showDivider();

            String input = Ui.readCommand();
            parser.parseInput(input, tasks);

            Ui.showDivider();
        }
    }
}
