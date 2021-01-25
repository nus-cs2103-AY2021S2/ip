import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "---------------------------------------------------------------------------";

    private final Scanner in;
    private final PrintStream out;

    /** The username of the user running the program. */
    public String username;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Greets the user when the Duke is launched.
     */
    public void showWelcomeMsg() {
        out.println(DIVIDER);
        out.println("Hello! I'm Jay!\n" + "What is your name!");
        out.println(DIVIDER);
    }

    /**
     * Greets the user again after knowing the user's name.
     */
    public void showNameMsg() {
        this.username = in.nextLine();
        out.println(DIVIDER);
        out.println("Hi " + this.username + "!");
        out.println("What can I do for you?");
        out.println(DIVIDER);
    }

    /**
     * Prompt for user to key in the next input.
     */
    public void getPrompt() {
        out.print(this.username + ": ");
    }

    /**
     * Show the user that a todo task has been added.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param totalTasks Total number of tasks in the list.
     */
    public void showTodoMsg(ArrayList<Task> tasks, int totalTasks) {
        out.println(DIVIDER);
        out.println("Got it. I've added this task:\n" + "    " + tasks.get(totalTasks - 1).toString());
        out.println("Now you have " + totalTasks + " tasks in the list.");
        out.println(DIVIDER);
    }

    /**
     * Show the user that a deadline task has been added.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param totalTasks Total number of tasks in the list.
     */
    public void showDeadlineMsg(ArrayList<Task> tasks, int totalTasks) {
        out.println(DIVIDER);
        out.println("Got it. I've added this task:\n" + "    " + tasks.get(totalTasks - 1).toString());
        out.println("Now you have " + totalTasks + " tasks in the list.");
        out.println(DIVIDER);
    }

    /**
     * Show the user that a event task has been added.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param totalTasks Total number of tasks in the list.
     */
    public void showEventMsg(ArrayList<Task> tasks, int totalTasks) {
        out.println(DIVIDER);
        out.println("Got it. I've added this task:\n" + "    " + tasks.get(totalTasks - 1).toString());
        out.println("Now you have " + totalTasks + " tasks in the list.");
        out.println(DIVIDER);
    }

    /**
     * Show the user that a task of choice has been deleted.
     * @param taskRemoved The description of the task deleted.
     * @param totalTasks Total number of tasks in the list.
     */
    public void showDeleteMsg(String taskRemoved, int totalTasks) {
        out.println(DIVIDER);
        out.println("Noted. I've removed this task:\n" + "    " + taskRemoved);
        out.println("Now you have " + totalTasks + " tasks in the list.");
        out.println(DIVIDER);
    }

    /**
     * Show the user that a task has been marked done.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param itemNum The item number that is marked done.
     */
    public void showDoneMsg(ArrayList<Task> tasks, int itemNum) {
        out.println(DIVIDER);
        out.println("Nice! I've marked this task as done:\n" + "    " + tasks.get(itemNum - 1).toString());
        out.println(DIVIDER);
    }

    /**
     * Show the user the list of tasks.
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @param totalTasks Total number of tasks in the list.
     */
    public void showListMsg(ArrayList<Task> tasks, int totalTasks) {
        out.println(DIVIDER);
        out.println("Here are the tasks in your list:");
        for (int i = 0; i < totalTasks; i++){
            int listNum = i + 1;
            out.println(listNum + ". " + tasks.get(i).toString());
        }
        out.println(DIVIDER);
    }

    /**
     * Showing user bye message when user ends the program.
     */
    public void showByeMsg() {
        out.println(DIVIDER);
        out.println("Bye " + this.username + "! Hope to see you again soon!");
        out.println(DIVIDER);
    }
}
