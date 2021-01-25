import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "---------------------------------------------------------------------------";

    private final Scanner in;
    private final PrintStream out;
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
    public void welcomeMsg() {
        out.println(DIVIDER);
        out.println("Hello! I'm Jay!\n" + "What is your name!");
        out.println(DIVIDER);
    }

    /**
     * Greets the user again after knowing the user's name.
     */
    public void nameMsg() {
        this.username = in.nextLine();
        out.println(DIVIDER);
        out.println("Hi " + this.username + "!");
        out.println("What can I do for you?");
        out.println(DIVIDER);
    }

    public void prompt() {
        out.print(this.username + ": ");
    }

    public void todoMsg(ArrayList<Task> tasks, int totalTasks) {
        out.println(DIVIDER);
        out.println("Got it. I've added this task:\n" + "    " + tasks.get(totalTasks - 1).toString());
        out.println("Now you have " + totalTasks + " tasks in the list.");
        out.println(DIVIDER);
    }

    public void deadlineMsg(ArrayList<Task> tasks, int totalTasks) {
        out.println(DIVIDER);
        out.println("Got it. I've added this task:\n" + "    " + tasks.get(totalTasks - 1).toString());
        out.println("Now you have " + totalTasks + " tasks in the list.");
        out.println(DIVIDER);
    }

    public void eventMsg(ArrayList<Task> tasks, int totalTasks) {
        out.println(DIVIDER);
        out.println("Got it. I've added this task:\n" + "    " + tasks.get(totalTasks - 1).toString());
        out.println("Now you have " + totalTasks + " tasks in the list.");
        out.println(DIVIDER);
    }

    public void deleteMsg(String taskRemoved, int totalTasks) {
        out.println(DIVIDER);
        out.println("Noted. I've removed this task:\n" + "    " + taskRemoved);
        out.println("Now you have " + totalTasks + " tasks in the list.");
        out.println(DIVIDER);
    }

    public void doneMsg(ArrayList<Task> tasks, int itemNum) {
        out.println(DIVIDER);
        out.println("Nice! I've marked this task as done:\n" + "    " + tasks.get(itemNum - 1).toString());
        out.println(DIVIDER);
    }

    public void listMsg(ArrayList<Task> tasks, int totalTasks) {
        out.println(DIVIDER);
        out.println("Here are the tasks in your list:");
        for (int i = 0; i < totalTasks; i++){
            int listNum = i + 1;
            out.println(listNum + ". " + tasks.get(i).toString());
        }
        out.println(DIVIDER);
    }

    public void byeMsg() {
        out.println(DIVIDER);
        out.println("Bye " + this.username + "! Hope to see you again soon!");
        out.println(DIVIDER);
    }
}
