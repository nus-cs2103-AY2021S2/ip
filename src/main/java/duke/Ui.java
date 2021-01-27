package duke;

import duke.task.Task;

import java.util.ListIterator;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Print message to user.
     * @param message Welcome/Goodbye message or a description of the task added.
     */

    public void print(String message) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\n\t  " + message);
        System.out.println("\n\t____________________________________________________________\n");
    }

    /**
     * Print all tasks.
     * @param tasks A list of tasks entered by the user.
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
