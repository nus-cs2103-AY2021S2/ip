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

    /**
     * Identifies the appropriate Task for a given user input and returns an instance.
     *
     * @param input User input string
     * @return A Task corresponding to an appropriate subclass instance of task
     */
    public static Task identifyAndCreateTask(String input) throws DukeException {
        String[] inputArr = input.split("\\s", 2);
        String taskType = inputArr[0].toLowerCase();

        String restOfInput;
        if (inputArr.length > 1) {
            restOfInput = inputArr[1];
        } else {
            throw new DukeException("Oops, The description of cannot be empty.");
        }

        Task task;
        switch (taskType) {
            case "todo":
                task = new ToDo(restOfInput);
                break;
            case "deadline":
                String[] deadlineArr = restOfInput.split("\\s+/by\\s+", 2);
                task = new Deadline(deadlineArr[0], deadlineArr[1]);
                break;
            case "event":
                String[] eventArr = restOfInput.split("\\s+/at\\s+", 2);
                task = new Event(eventArr[0], eventArr[1]);
                break;
            default:
                throw new DukeException("Invalid command was passed");
        }
        task.printInstantiationText();
        return task;
    }

    // ########## Instance methods ##########

    /**
     * Activates the chat-bot so that it keeps taking inputs from the user via System.in
     * until the "bye" input is given.
     *
     * Additionally, dispatches the user input into one of three channels:
     * 1. Terminate command
     * 2. Non-terminate command
     * 3. New tasking
     */
    public void run() {
        while (true) {
            String input = scanner.nextLine();
            if (input.startsWith("delete")) {
                int deleteId = Integer.parseInt(input.split("\\s")[1]) -1;
                Task task = list.get(deleteId);
                list.remove(deleteId);
                System.out.println("Noted ive removed this task:");
                System.out.println(task);
                System.out.println("Now you have " + list.size() + " tasks in the list");
            }
            else if (DukeBot.isTerminateCommand(input)) {
                System.out.println(UNDERLINES);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(UNDERLINES);
                break;
            } else if (DukeBot.isCommand(input)) {
                try {
                    this.handleCommand(input);
                } catch (DukeException e) {
                    System.out.println(e);
                }
            } else {
                // Not a command at all -> Add new task
                try {
                    this.list.add(DukeBot.identifyAndCreateTask(input));
                } catch (DukeException e) {
                    System.out.println(e);
                }
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
    public void handleCommand(String input) throws DukeException {
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
                throw new DukeException("An inappropriate command was given.");
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

