import java.text.NumberFormat;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main implementation for the Duke chat-bot.
 * @author Soon Keat Neo
 * @version CS2103T AY20/21 Sem 1 iP v0.1
 */

public class Duke {
    /** Allows for easy change of the bot name in future. **/
    final private static String BOTNAME = "DukeNukem";
    final private static String SEPARATORS = "~~~~~~~~~~~~~~~~~~~~~~";
    final private static List<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        greetUser();
        listenInput();
        quit();
    }

    /**
     * Greets user with a message on the screen when the function is called.
     */
    public static void greetUser() {
        printMessage("Henlooooo~! My name is " + BOTNAME);
        printMessage("What can I do for you today? :)");
    }

    /**
     * Prints separators providing for clarity of on-screen text.
     */
    public static void printSeparators() {
        System.out.println(SEPARATORS);
    }

    /**
     * Print the requested message in the bot's formatting
     * @param message The message to be printed
     */
    public static void printMessage(String message) {
        System.out.println("    " + message);
    }

    /**
     * Prints the task added and the total size of the list.
     * @param addedTask Task that was added to the list.
     */
    public static void addedTaskReply(Task addedTask) {
        printMessage("Wakarimashita! Task added to list:");
        printMessage(addedTask.toString());
        printMessage("The size of your task list is now: " + taskList.size());
    }

    /**
     * Mark the task passed to the method as complete.
     * @param inputString User input string.
     */
    public static void completeTask(String inputString) {
        try {
            int taskId = Integer.parseInt(String.valueOf(inputString.split(" ")[1])) - 1;
            Task doneTask = taskList.get(taskId).setDone();
            printMessage("Great~! Task completed:");
            printMessage(doneTask.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Delete the specified task from the list.
     * @param inputString User input string.
     */
    public static void deleteTask(String inputString) {
        try {
            int taskId = Integer.parseInt(String.valueOf(inputString.split(" ")[1])) - 1;
            Task deletedTask = taskList.remove(taskId);
            printMessage("Okie! I've deleted the task from your list:");
            printMessage(deletedTask.toString());
            printMessage("The size of your task list is now: " + taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Listens to the user's input, and passes it to the input handler.
     */
    public static void listenInput() {
        Scanner scannerObject = new Scanner(System.in);
        boolean stillListening = true;
        while (stillListening) {
            printSeparators();
            String inputString = scannerObject.nextLine().trim();;
            printSeparators();
            try {
                handleInput(inputString);
            } catch (InvalidCommandException | InvalidInputException | InvalidTaskException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Handle the input and passes to the relevant methods.
     * @param inputString User input string to be handled.
     */
    public static void handleInput(String inputString) {

        if (inputString.equals("list")) {
            printList();
        } else if (inputString.equals("bye")) {
            quit();
        } else if (inputString.startsWith("done")) {
            completeTask(inputString);
        } else if (inputString.startsWith("delete")) {
            deleteTask(inputString);
        } else if (inputString.startsWith("todo")) {
            try {
                Todo newTodo = new Todo(inputString.substring(5));
                taskList.add(newTodo);
                addedTaskReply(newTodo);
            } catch (StringIndexOutOfBoundsException e) {
                throw new InvalidInputException();
            }
        } else if (inputString.startsWith("event")) {
            try {
                String[] eventString = inputString.split("/at");
                String taskString = eventString[0].substring(6).trim();
                String eventTime = eventString[1].trim();
                Event newEvent = new Event(taskString, eventTime);
                taskList.add(newEvent);
                addedTaskReply(newEvent);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidInputException();
            }
        } else if (inputString.startsWith("deadline")) {
            try {
                String[] eventString = inputString.split("/by");
                String taskString = eventString[0].substring(9).trim();
                String deadlineTime = eventString[1].trim();
                Deadline newDeadline = new Deadline(taskString, deadlineTime);
                taskList.add(newDeadline);
                addedTaskReply(newDeadline);
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                throw new InvalidInputException();
            }
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Prints the list of tasks in the list, including the status.
     */
    public static void printList() {
        if (taskList.size() < 1) {
            printMessage("There are no tasks in your list! :c");
            return;
        }
        System.out.println("    Tasks in your list are~: ");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println("    " + (i + 1) + "." + task);
        }
    }

    /**
     * Quits the program and provides provisions for clean-up.
     */
    public static void quit() {
        printMessage("Hope you had an enjoyable experience! Good-bye~");
        System.exit(0);
    }
}
