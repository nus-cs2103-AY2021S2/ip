import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Static factory creation of Ui.
     *
     * @return Ui object started.
     */
    public static Ui startUi() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        return new Ui();
    }

    /**
     * Reads a line of user input, returns null if there is no input.
     *
     * @return String read.
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return null;
        }
    }

    /**
     * Closes this Ui.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Prints error message.
     *
     * @param err Error message.
     */
    public static void printErr(String err) {
        System.out.println(err);
    }

    public static void printLine() {
        System.out.println("------------------------------");
    }

    /**
     * Prints each Task in the list with its index, and remarks if the list is empty.
     *
     * @param tasks List of Tasks to be printed.
     */
    public static void printList(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("Your list is empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + "." + tasks.get(i - 1));
            }
        }
    }

    /**
     * Throws IllegalArgumentException if the first word of the command is wrong or absent.
     *
     * @param command User input provided.
     * @throws IllegalArgumentException if the first word of the command is wrong or absent.
     */
    public static void throwIllegalArgumentEx(String command) throws IllegalArgumentException {
        if (command.length() > 0) {
            throw new IllegalArgumentException("That is not a valid command!\n" +
                    "These are the possible commands:\n" +
                    "\"list\" \"done\" \"delete\" \"todo\" \"deadline\" \"event\" \"bye\"");
        } else {
            throw new IllegalArgumentException("What are you trying to say?\n" +
                    "Please enter something ...");
        }
    }
}
