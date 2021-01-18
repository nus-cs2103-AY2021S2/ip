import java.util.Scanner;
import java.util.ArrayList;

/**
 * Duke is a project that eventually builds into a personal assistant chat bot.
 */
public class Duke {

    //tracks all tasks
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Entry point of the program, first greets then listens for input from user.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        greet();
        listenInput();
    }

    /**
     * Listens for input from the user.
     */
    public static void listenInput() {
        Scanner sc = new Scanner(System.in);
        String input;

        while (sc.hasNextLine()) {
            System.out.println("------------------------------------");

            input = sc.nextLine();

            //program exits on bye
            if (input.equals("bye")) {
                Command.exit();
            //program shows entered tasks on list
            } else if (input.equals("list")) {
                Command.list(tasks);
            //program marks task as complete on done
            } else if (input.startsWith("done")) {
                Command.done(input, tasks);
            //program adds task
            } else if (input.startsWith("todo")
                || input.startsWith("deadline")
                || input.startsWith("event"))
            {
                Command.add(input, tasks);
            //program echoes invalid instructions otherwise
            } else {
                echo(input);
            }

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

    /**
     * Prints and informs invalid input given by the user.
     * @param input input provided by user
     */
    public static void echo(String input) {
        System.out.println("Sorry, I do not recognise the following instruction:\n" + input);
    }
}
