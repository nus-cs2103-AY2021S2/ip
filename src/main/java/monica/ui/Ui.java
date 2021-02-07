package monica.ui;

import monica.task.TaskList;
import monica.task.Task;

/**
 * Represents the responses to the user.
 */
public class Ui {

    /**
     * Displays the welcome message.
     * @return string
     */
    public static String showWelcome() {
        return "Hi there! I'm Monica.\nWhat can I do for you?\n";
    }

    /**
     * Displays a message after a task is added to the list.
     * @param task Task to be added.
     * @param tasks Task List.
     */
    public String showAdded(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n  " + task
                + "\nNow you have " + tasks.getSize() + " task"
                + (tasks.getSize() > 1 ? "s in the list." : " in the list.");
    }

    /**
     * Displays a message after a task is deleted from the list.
     * @param task Task to be deleted.
     * @param tasks Task List.
     */
    public String showDeleted(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n  " + task
                + "\nNow you have " + tasks.getSize() + " task"
                + (tasks.getSize() > 1 ? "s in the list." : " in the list.");

    }

    /**
     * Displays a message after a task is done.
     * @param id Task ID.
     * @param tasks Task List.
     */
    public  String showDone(int id, TaskList tasks) {
        return "Nice! I've marked this task as done:\n" + tasks.getTask(id);
    }

    /**
     * Displays all the tasks when the user requests to view task list.
     * @param tasks Task List.
     */
    public String showList(TaskList tasks) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");

        if (tasks.getSize() == 0) {
            output.append("There is no task in the list.");
        } else {
            for (int i = 1; i <= tasks.getSize(); i++) {
                output.append(i).append(". ").append(tasks.getTask(i)).append("\n");
            }
        }
        return output.toString();
    }

    /**
     * Displays all the tasks that contain the keyword.
     * @param keyword Keyword to be found.
     * @param tasks Task List used to find the matching tasks.
     */
    public String showFound(String keyword, TaskList tasks) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = 1;

        for (int i = 1; i <= tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                output.append(count).append(". ").append(tasks.getTask(i)).append("\n");
                count++;
            }
        }

        if (count == 1) {
            output.append("There is no matching task in the list. You can try another keyword.");
        }
        return output.toString();

    }


    /**
     * Displays error messages when an exception is caught.
     * @param error Error being caught.
     */
    public String showError(Exception error) {
        return error.toString();
    }

    /**
     * Displays farewell message.
     */
    public static String sayBye() {
        return "Bye. Hope to see you again soon!\n";
    }
}
