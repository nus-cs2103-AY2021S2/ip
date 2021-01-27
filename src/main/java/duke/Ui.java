package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the greeting message.
     */
    public void greeting() {

        System.out.println("    ---------------------------------------");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ---------------------------------------");
    }

    /**
     * Prints the bye.
     */
    public void goodbye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    /**
     * Prints the divider line.
     */
    public void linkBreaker() {
        System.out.println("    ---------------------------------------");
    }

    /**
     * Prints the error message.
     */
    public void errorLine(String message) {
        System.out.println("    Error: " + message);
    }

    /**
     * Prints the tasks in the list.
     */
    public void taskListLine(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            System.out.println("    "
                    + index + ": " + tasks.get(i));
        }

        if (tasks.size() == 0) {
            System.out.println("    "
                    + "--- There is no task ---");
        }
    }

    public void printLine(String text) {
        System.out.println("    " + text);
    }

    /**
     * Returns the next command line.
     *
     * @return next user input.
     */
    public String nextCommand() {
        return sc.nextLine();
    }
}
