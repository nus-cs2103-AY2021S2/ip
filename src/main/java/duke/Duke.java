package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.history.RedoHistory;
import duke.history.UndoHistory;
import duke.task.CommandManager;
import duke.task.Task;

/**
 * Duke manages command line inputs.
 * It takes and handles inputs from the user.
 *
 * @author  Nicole Ang
 */
public class Duke {
    public static final String LINE = (char) 9
            + "--------------------------------------------------------------------";
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected UndoHistory undoHistory = new UndoHistory();
    protected RedoHistory redoHistory = new RedoHistory();

    /**
     * Takes user inputted tasks and passes them to the CommandManager.
     *
     * @param args  Java command line inputs.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! How can I help you?");

        Scanner scanner = new Scanner(System.in);
        tasks = new ArrayList<>();
        CommandManager taskManager = new CommandManager();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(LINE + "\n" + (char) 9 + (char) 9 + "Bye! See you soon :)\n" + LINE);
                scanner.close();
                break;
            } else {
                System.out.println(taskManager.takeCommand(input, tasks));
            }

        }
        return;
    }
}
