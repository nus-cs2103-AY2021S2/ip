import java.util.Scanner;

/**
 * Handles I/O with the user.
 */
public class Ui {

    private static final String LINE = "____________________________________________________________";

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Asks for user input.
     * @return User input
     */
    public String askForInput() {
        return scanner.nextLine();
    }

    /**
     * Greets the user.
     */
    public String greet() {
        return "Hello! I'm Duke" + "\n" + "What can I do for you?";
    }

    /**
     * Says bye to the user.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Display added task.
     * @param task Added task
     */
    public String displayAdded(Task task) {
        return "I've added this task: \n" + task.toString();
    }

    /**
     * Display done task.
     * @param task Done task
     */
    public String displayDone(Task task) {
        return "Marked as done: \n" + task;
    }

    /**
     * Display deleted task.
     * @param task Deleted task
     */
    public String displayDeleted(Task task) {
        return "I removed this task: \n" + task;
    }

    /**
     * Display error message.
     * @param message Error message
     */
    public String displayError(String message) {
        return "ERROR: " + message;
    }

    /**
     * Print message wrapped with lines.
     * @param input input to print
     */
    public void print(String input) {
        System.out.println(LINE + "\n" + input + "\n" + LINE + "\n");
    }
}