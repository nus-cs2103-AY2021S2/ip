import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static List<Task> list;

    /**
     * An application that serves as a to-do list.
     * Exit the program by entering "bye".
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        list = new ArrayList<>();
        System.out.println("POWERED BY JARVIS\n");
        greet();
        String userInput = sc.nextLine();
        String[] parseInput = userInput.split(" ");
        String command = parseInput[0];
        while (!userInput.equals("bye")) {
            switch (command) {
                case "list":
                    print(list);
                    break;
                case "todo":
                    addTask(new ToDo(userInput.substring(5)));
                    break;
                case "deadline":
                    String[] parseDeadline = userInput.substring(9).split(" /by ");
                    addTask(new Deadline(parseDeadline[0], parseDeadline[1]));
                    break;
                case "event":
                    String[] parseEvent = userInput.substring(6).split(" /at ");
                    addTask(new Event(parseEvent[0], parseEvent[1]));
                    break;
                case "done":
                    int taskIndex = Integer.parseInt(parseInput[1])-1;
                    markTaskAsDone(list.get(taskIndex));
                    break;
                default:
                    print("Invalid Command Entered!");
            }
            userInput = sc.nextLine();
            parseInput = userInput.split(" ");
            command = parseInput[0];
        }
        exit();
    }

    /**
     * Greet the user when he/she starts the application.
     */

    private static void greet() {
        print("Hello! I'm Jarvis.\n\t  How may I help you?");
    }

    /**
     * Say goodbye to the user when he/she exits the application.
     */

    private static void exit() {
        print("Goodbye. See you later!");
    }

    /**
     * Add task to the list.
     * @param task Task entered by the user.
     */

    private static void addTask(Task task) {
        list.add(task);
        print("Got it. I've added this task:\n\t\t" + task +
                "\n\n\t  You have " +
                list.size() + (list.size() == 1 ? " task" : " tasks") + " in your list");
    }

    /**
     * Mark task as completed.
     * @param task Task entered by the user.
     */

    private static void markTaskAsDone(Task task) {
        task.markDone();
        print("Nice! I have marked this task as done:\n\t\t " + task);
    }

    /**
     * Print message to user.
     * @param message Welcome/Goodbye message or a description of the task added.
     */

    public static void print(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\n\t  " + message);
        System.out.println("\n\t____________________________________________________________\n");
    }

    /**
     * Print all tasks.
     * @param list A list of tasks entered by the user.
     */

    public static void print(List<Task> list) {
        System.out.println("\t____________________________________________________________\n");
        System.out.println("\t  Your tasks:");
        int listCounter = 1;
        for (Task task : list) {
            System.out.println("  \t  " + listCounter + "." + task);
            listCounter++;
        }
        System.out.println("\n\t____________________________________________________________\n");
    }
}
