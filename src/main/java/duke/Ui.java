package duke;

import java.util.List;

/**
 * Deals with User Interaction jobs.
 */
public class Ui {
    protected static final String SEPERATOR_LINE = "-----------------------------";

    /**
     * Greet user.
     */
    public static void greet() {
        String welcome = "Hi, I'm Duke and I'm gonna be your assistant. Enjoy!!!";
        System.out.println(welcome);
        System.out.println();
    }

    /**
     * Say goodbye to user when exit.
     */
    public static void exit() {
        System.out.println(SEPERATOR_LINE);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(SEPERATOR_LINE);
    }

    /**
     * Print feedback after an addition of a task.
     */
    public static void addTask(List<Task> lst) {
        System.out.println(SEPERATOR_LINE);
        System.out.println("Got it. Now I have added this "
                + "task:\n" + "  " + lst.get(lst.size() - 1) + "\n"
                + "Now you have " + lst.size() + " tasks in the list.");
        System.out.println(SEPERATOR_LINE);
    }

    /**
     * Print feedback after marking a task as done.
     */
    public static void markDone(Task task) {
        System.out.println(SEPERATOR_LINE);
        System.out.println("Nice! I've marked this task as done:\n"
                + "  " + task + "\n");
        System.out.println(SEPERATOR_LINE);
    }

    /**
     * Print feedback after deleting a task.
     */
    public static void delete(List<Task> lst, Task task) {
        System.out.println(SEPERATOR_LINE);
        System.out.println("Noted. I've removed this "
                + "task:\n" + "  " + task + "\n"
                + "Now you have " + lst.size() + " tasks in the list.");
        System.out.println(SEPERATOR_LINE);
    }

    /**
     * List all the tasks stored.
     */
    public static void list(List<Task> lst) {
        System.out.println(SEPERATOR_LINE);
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + ". " + lst.get(i));
        }
        System.out.println(SEPERATOR_LINE);
    }

    /**
     * Prints feedback of the find command.
     */
    public void find(List<Task> lst) {
        System.out.println(SEPERATOR_LINE);
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + ". " + lst.get(i));
        }
        System.out.println(SEPERATOR_LINE);
    }

    /**
     * Print error message.
     */
    public static void printException(DukeException err) {
        System.out.println(SEPERATOR_LINE);
        System.out.println(err.getMessage());
        System.out.println(SEPERATOR_LINE);
    }
}
