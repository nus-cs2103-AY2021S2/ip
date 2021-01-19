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
    static private List<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        greetUser();
        listenInput();
        quit();
    }

    /**
     * Greets user with a message on the screen when the function is called.
     */
    public static void greetUser() {
        String greetingString = "   Henlooooo~! My name is " + BOTNAME + "!\n" +
                "   What can I do for you today? :)";
        System.out.println(greetingString);
    }

    /**
     * Prints separators providing for clarity of on-screen text.
     */
    public static void printSeparators() {
        System.out.println(SEPARATORS);
    }

    /**
     * Listens to the user's input, adds to list or prints appropriately.
     */
    public static void listenInput() {
        Scanner scannerObject = new Scanner(System.in);
        boolean stillListening = true;
        String inputString;
        while (stillListening) {
            printSeparators();
            inputString = scannerObject.nextLine().trim();
            printSeparators();
            if (inputString.equals("list")) {
                printList();
            } else if (inputString.equals("bye")) {
                quit();
            } else if (inputString.startsWith("done")) {
                try {
                    int taskId = Integer.parseInt(String.valueOf(inputString.split(" ")[1])) - 1;
                    Task doneTask = taskList.get(taskId).setDone();
                    System.out.println("Great~! Task completed:");
                    System.out.println("    " + doneTask);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Hey!!! You made an error in the number >:(");
                    System.out.println("Check and try it again!");
                }
            } else if (inputString.startsWith("todo")) {
                    taskList.add(new Todo(inputString.substring(5)));
                    System.out.println("    Wakarimashita! Task added to list:");
                    System.out.println("      " + taskList.get(taskList.size() - 1));
                    System.out.println("    The size of your task list is now: " + taskList.size());
                } else if (inputString.startsWith("event")) {
                    String[] eventString = inputString.split("/at");
                    String taskString = eventString[0].substring(6);
                    String eventTime = eventString[1];
                    taskList.add(new Event(taskString, eventTime));
                    System.out.println("    Wakarimashita! Task added to list:");
                    System.out.println("      " + taskList.get(taskList.size() - 1));
                    System.out.println("    The size of your task list is now: " + taskList.size());
                } else if (inputString.startsWith("deadline")) {
                    String[] eventString = inputString.split("/by");
                    String taskString = eventString[0].substring(9);
                    String deadlineTime = eventString[1];
                    taskList.add(new Deadline(taskString, deadlineTime));
                    System.out.println("    Wakarimashita! Task added to list:");
                    System.out.println("      " + taskList.get(taskList.size() - 1));
                    System.out.println("    The size of your task list is now: " + taskList.size());
                }

            }
    }

    /**
     * Prints the list of tasks in the list, including the status.
     */
    public static void printList() {
        if (taskList.size() < 1) {
            System.out.println("    There are no tasks in your list! :c");
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
        System.out.println("    Hope you had an enjoyable experience! Good-bye~");
        System.exit(0);
    }
}
