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
    public void greet() {
        String greeting = "Hello! I'm Duke" + "\n" + "What can I do for you?";
        print(greeting);
    }

    /**
     * Says bye to the user.
     */
    public void bye() {
        String output = "Bye. Hope to see you again soon!";
        print(output);
    }

    /**
     * Display added task.
     * @param task Added task
     */
    public void displayAdded(Task task) {
        String output = "I've added this task: \n" + task.toString();
        print(output);
    }

    /**
     * Display done task.
     * @param task Done task
     */
    public void displayDone(Task task) {
        String output = "Marked as done: \n" + task;
        print(output);
    }

    /**
     * Display deleted task.
     * @param task Deleted task
     */
    public void displayDeleted(Task task) {
        String output = "I removed this task: \n" + task;
        print(output);
    }

    /**
     * Display error message.
     * @param message Error message
     */
    public void displayError(String message) {
        String output = "ERROR: " + message;
        print(output);
    }

    /**
     * Print message wrapped with lines.
     * @param input input to print
     */
    public void print(String input) {
        System.out.println(LINE + "\n" + input + "\n" + LINE + "\n");
    }
}