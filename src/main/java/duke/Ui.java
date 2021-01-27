package duke;

import duke.task.Task;

import java.util.ListIterator;
import java.util.Scanner;

/**
 * Class containing methods that interacts with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor of Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads a line of user input and returns it.
     *
     * @return String of user input.
     */
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Formats and prints message to user.
     *
     * @param message Message to be printed.
     */

    public void print(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\n\t  " + message);
        System.out.println("\n\t____________________________________________________________\n");
    }

    /**
     * Formats and prints all tasks.
     * 
     * @param tasks A list of user's tasks.
     */

    public void print(TaskList tasks) {
        System.out.println("\t____________________________________________________________\n");
        System.out.println("\t  Your tasks:");
        int listCounter = 1;
        ListIterator<Task> iterator = tasks.getIterator();
        Task task;
        while (iterator.hasNext()) {
            task = iterator.next();
            System.out.println("  \t  " + listCounter + "." + task);
            listCounter++;
        }
        System.out.println("\n\t____________________________________________________________\n");
    }
}
