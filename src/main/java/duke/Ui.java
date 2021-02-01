package duke;

import java.util.ArrayList;

/**
 * User interface class for Duke's interaction with users, send messages etc.
 */
public class Ui {

    protected static final String horizontalLine = "____________________________________________________________";

    /**
     * Constructor for Ui class.
     */
    public Ui() {

    }

    /**
     * Prints greetings message at the start when user runs the Duke app.
     */
    public void greetings() {
        System.out.println("____________________________________________________________\n" +
                "Hello! I'm your personal assistant Duke\n" +
                "How can I assist you?\n" +
                "____________________________________________________________");
    }

    /**
     * Prints exit message when user inputs "bye".
     */
    public void exit() {
        System.out.println("Bye. Till next time!");
        System.out.println(horizontalLine);
    }

    /**
     * Prints message when user adds a task to list
     * @param list list of tasks
     * @param addedTask
     */
    public void addTaskMessage(ArrayList<Task> list, Task addedTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println(addedTask);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    /**
     * Prints message when user deletes a task from list
     * @param list list of tasks
     * @param deletedTask task that is deleted
     */
    public void deleteTaskMessage(ArrayList<Task> list, Task deletedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    /**
     * Prints message when user requests for tasks in list
     * @param list list of tasks
     */
    public void listAllTasks(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        int num = 1;
        for (Task task : list) {
            System.out.println(String.format("%d. %s", num, task));
            num++;
        }
        System.out.println(horizontalLine);
    }

    /**
     * Prints message when user checks a task as done
     * @param task task that is checked as done
     */
    public void checkAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
        System.out.println(horizontalLine);
    }

}
