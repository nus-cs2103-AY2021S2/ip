import java.util.Scanner;
import java.util.ArrayList;

/**
 * Duke is a project that eventually builds into a personal assistant chat bot.
 */
public class Duke {

    //tracks all tasks
    public static ArrayList<Task> tasks = DataHandler.loadData();

    /**
     * Entry point of the program, first greets then listens for input from user.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Command command = new Command();
        greet();
        listenInput(command);
    }

    /**
     * Listens for input from the user.
     * @param command command class that handles parsing of input
     */
    public static void listenInput(Command command) {
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("------------------------------------");

        while (sc.hasNextLine()) {
            System.out.println("------------------------------------");

            input = sc.nextLine();
            command.parseInput(input, tasks);

            System.out.println("------------------------------------");
        }
    }

    /**
     * Greets the user upon program launch.
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
    }
}
