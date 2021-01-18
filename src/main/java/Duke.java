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

        Command command = new Command();
        greet();
        listenInput(command);
    }

    /**
     * Listens for input from the user.
     */
    public static void listenInput(Command command) {
        Scanner sc = new Scanner(System.in);
        String input;

        while (sc.hasNextLine()) {
            System.out.println("------------------------------------");

            input = sc.nextLine();

            //program exits on bye
            if (input.equals("bye")) {
                command.exit();
            //program shows entered tasks on list
            } else if (input.equals("list")) {
                command.list(tasks);
            //program marks task as complete on done
            } else if (input.startsWith("done")) {
                command.done(input, tasks);
            //program removes task on delete
            } else if (input.startsWith("delete")) {
                command.delete(input, tasks);
            //program list help commands
            } else if (input.equals("help")) {
                command.help();
            //program tries to add task otherwise
            } else {
                command.add(input, tasks);
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
}
