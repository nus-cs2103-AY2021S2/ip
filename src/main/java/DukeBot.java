import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/**
 * Duke is a CLI chat-bot that handles task tracking.
 *
 * @author Douglas Wei Jing Allwood
 * @author douglas_allwood@u.nus.edu
 */
public class DukeBot {
    private Scanner scanner;
    private List<Task> list;
    private static final String UNDERLINES = "____________________________________________________________";

    public DukeBot(Scanner scanner) {
        this.scanner = scanner;
        this.list = new ArrayList<Task>(100);
    }

    // ########## Static methods ##########

    /**
     * Returns the n-th word present in a given line of input text.
     *
     * If n > the number of words present or n < 1, throws ArrayIndexOutOfBoundsException.
     *
     * @param input The user input text of String type
     * @return A lowercase String
     */
    public static String getNthWord(String input, int n) {
        return String.valueOf(input).split("\\s", 2)[n-1].toLowerCase();
    }

    /**
     * Returns true if the user input String should be treated as a command
     * instead of a new task.
     *
     * @param input User input String
     * @return true if input is to be treated as a command and false otherwise
     */
    public static boolean isCommand(String input) {
        String firstWord = DukeBot.getNthWord(input, 1);
        Command command = Command.get(firstWord);
        return command != null;
    }

    /**
     * Returns true if the user input String should be treated as a terminate command.
     *
     * @param input User input String
     * @return true if input is a terminate command
     */
    public static boolean isTerminateCommand(String input) {
        String firstWord = DukeBot.getNthWord(input, 1);
        Command command = Command.get(firstWord);
        return command == Command.END;
    }

    // ########## Instance methods ##########

    /**
     * Activates the chat-bot so that it keeps taking inputs from the user via System.in
     * until the "bye" input is given.
     */
    public void run() {
        while (true) {
            String input = scanner.nextLine();
            if (DukeBot.isTerminateCommand(input)) {
                System.out.println(UNDERLINES);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(UNDERLINES);
                break;
            } else if (DukeBot.isCommand(input)) {
                this.handleCommand(input);
            } else {
                // Not a command at all -> Add new task
                this.list.add(new Task(input));
            }
        }
        this.scanner.close();
    }

    /**
     * Handles commands given in the user input string appropriately.
     *
     * Each command will invoke different behaviour from the DukeBot chat-bot.
     *
     * @param input The user input that contains the command its first word
     */
    public void handleCommand(String input) {
        String firstWord = DukeBot.getNthWord(input, 1);
        Command command = Command.get(firstWord);

        switch (command) {
            case LIST:
                this.printList();
                break;
            case DONE:
                int taskIndex = Integer.parseInt(DukeBot.getNthWord(input, 2));
                list.get(taskIndex - 1).markAsDone();
                break;
            default:
                throw new IllegalArgumentException("An inappropriate command was given.");
        }
    }

    /**
     * Prints all the existing list items that this current chat-bot was made to track.
     */
    public void printList() {
        System.out.println(UNDERLINES);
        int count = 1;
        for (Task task : this.list) {
            System.out.println(" " + count + ". " + task);
            count++;
        }
        System.out.println(UNDERLINES);
    }
}

