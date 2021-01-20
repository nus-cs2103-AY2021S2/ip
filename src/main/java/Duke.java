import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 * Todo:
 *   - exceptions yet to be handled:
 *     - number of tasks > 100
 *     - multiple spaces in between tokens
 *     - done command
 *       - w/o number
 *       - number out of range
 *   - help command
 *   - Task as abstract class with 3 subclasses (T/D/E)
 *   - TaskList as a class
 */

public class Duke {

    /**
     * The task list
     */
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * All exit commands are listed here
     */
    private static final Set<String> EXIT_COMMANDS = Set.of(
            "bye", "exit", "quit"
    );

    /**
     * Print one line with spaces in front
     * @param line the line to print
     */
    public static void printLine(String line) {
        System.out.println("     " + line);
    }

    /**
     * Print a horizontal line
     */
    public static void printHorizontalLine() {
        System.out.println("    " + "____________________________________________________________");
    }

    /**
     * Print an empty line
     */
    public static void printEmptyLine() {
        System.out.println();
    }

    /**
     * Process a command
     * @param command the command to process
     * @return whether the program should continue (<code>true</code> if not an exit command)
     */
    public static boolean processCommand(String command) {
        String[] tokens = command.split(" ");
        printHorizontalLine();
        if (EXIT_COMMANDS.contains(command)) {
            printLine("Bye. Hope to see you again soon!");
        } else {
            if (tokens[0].equals("list")) {
                printLine("Here are the tasks in your list:");
                int index = 0;
                for (Task task : tasks) {
                    printLine(String.format("%d.[%s] %s", ++index, task.getIsDone() ? "X" : " ", task.getName()));
                }
            } else if (tokens[0].equals("done")) {
                if (Integer.parseInt(tokens[1]) <= tasks.size()) {
                    tasks.get(Integer.parseInt(tokens[1]) - 1).setIsDone(true);
                    printLine("Nice! I've marked this task as done:");
                    printLine(String.format("  [X] %s", tasks.get(Integer.parseInt(tokens[1]) - 1).getName()));
                }
            } else {
                tasks.add(new Task(command));
                printLine(String.format("added: %s", command));
            }
        }
        printHorizontalLine();
        printEmptyLine();
        return !EXIT_COMMANDS.contains(command);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = "██████╗ ██╗   ██╗██╗  ██╗███████╗\n" +
                "██╔══██╗██║   ██║██║ ██╔╝██╔════╝\n" +
                "██║  ██║██║   ██║█████╔╝ █████╗  \n" +
                "██║  ██║██║   ██║██╔═██╗ ██╔══╝  \n" +
                "██████╔╝╚██████╔╝██║  ██╗███████╗\n" +
                "╚═════╝  ╚═════╝ ╚═╝  ╚═╝╚══════╝\n";
        printHorizontalLine();
        printEmptyLine();
        for (String line : logo.split("\n")) {
            printLine(line);
        }
        printEmptyLine();
        printLine("Who is the ultimate Personal Assistant Chatbot that helps keep track of various things?");
        printLine("そう、私です！");
        printHorizontalLine();
        printEmptyLine();
        while (processCommand(sc.nextLine())) ;
    }
}
