import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Duke {

    /**
     * The task list
     */
    public static ArrayList<String> tasks = new ArrayList<String>();

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
        printHorizontalLine();
        if (EXIT_COMMANDS.contains(command)) {
            printLine("Bye. Hope to see you again soon!");
        } else {
            switch (command) {
                case "list":
                    int index = 0;
                    for (String task : tasks) {
                        printLine(String.format("%d: %s", ++index, task));
                    }
                    break;
                default:
//                printLine(command);
                    tasks.add(command);
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
        printLine("Hello! I'm Duke");
        printLine("What can I do for you?");
        printHorizontalLine();
        printEmptyLine();
        while (processCommand(sc.nextLine())) ;
    }
}
