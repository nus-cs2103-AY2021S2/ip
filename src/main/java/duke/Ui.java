package duke;

import java.util.ArrayList;


/**
 * User interface class for Duke's interaction with users, send messages etc.
 */
public class Ui {

    protected static final String HORIZONTAL_RULE = "____________________________________________________________";

    /**
     * Constructor for Ui class.
     */
    public Ui() {

    }

    /**
     * Prints greetings message at the start when user runs the Duke app.
     */
    public static String greetings() {
        String message = "Hello! I'm your personal assistant Fluffy\n"
                + "How can I assist you?";
        return message;
    }

    /**
     * Prints exit message when user inputs "bye".
     */
    public String exit() {
        String message = "Bye. Till next time!";
        return message;
    }

    /**
     * Prints message when user adds a task to list
     * @param list list of tasks
     * @param addedTask task that is added
     */
    public String addTaskMessage(ArrayList<Task> list, Task addedTask) {
        String message = "Got it. I've added this task:\n"
                + addedTask + "\n"
                + "Now you have " + list.size() + " tasks in the list.";
        return message;
    }

    /**
     * Prints message when user deletes a task from list
     * @param list list of tasks
     * @param deletedTask task that is deleted
     */
    public String deleteTaskMessage(ArrayList<Task> list, Task deletedTask) {
        String message = "Noted. I've removed this task:\n"
                + deletedTask + "\n"
                + "Now you have " + list.size() + " tasks in the list.";
        return message;
    }


    /**
     * Prints message when user checks a task as done
     * @param task task that is checked as done
     */
    public String checkAsDoneMessage(Task task) {
        String message = "Nice! I've marked this task as done:\n" + task;
        return message;
    }

    /**
     * Prints message when user requests for tasks in list
     * @param list list of tasks
     */
    public String listAllTasks(ArrayList<Task> list) {
        String message = "Here are the tasks in your list:\n";
        int num = 1;
        for (Task task : list) {
            message += String.format("%d. %s", num, task) + "\n";
            num++;
        }
        return message;
    }

    /**
     * Prints matching tasks in list
     * @param keyword keyword to search for in list
     * @param list list of tasks
     */
    public String findTask(String keyword, ArrayList<Task> list) {
        ArrayList<Task> newList = new ArrayList<>();
        String message = "Here are the matching tasks in your list:\n";
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                newList.add(task);
            }
        }
        if (newList.size() == 0) {
            message += "No matching tasks found. :^S";
        }
        else {
            for (Task task : newList) {
                if (task.getDescription().contains(keyword)) {
                    message += task + "\n";
                }
            }
        }
        return message;
    }
}
