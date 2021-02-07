package duke;

import java.util.ArrayList;

/**
 * Represents the component of the Duke program
 * that deals with interactions with the user.
 */
public class Ui {

    protected static final String TASK_CONFIRMATION = "Got it. I've added this task:\n";

    /**
     * Returns a String message informing the user
     * of the Task added to the TaskList
     * along with the total number of Tasks in the TaskList.
     *
     * @param t        the Task that was added to the TaskList.
     * @param taskList the TaskList in which the Task was added to.
     * @return String message informing the user of the Task added to the TaskList.
     */
    public String getTaskAddedConfirmation(Task t, TaskList taskList) {
        int numTasks = taskList.getTasks().size();
        return TASK_CONFIRMATION + t
                + "\nNow you have " + numTasks
                + (numTasks < 2 ? " task " : " tasks ") + "in the list.\n";
    }

    /**
     * Returns a String message containing the details of
     * all the Tasks in the TaskList in numerical order.
     *
     * @param taskList the TaskList containing the Tasks to be printed.
     * @return String message containing all Tasks in the TaskList.
     */
    public String getAllTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            return "There are no tasks in your list. Hooray!\n";
        } else {
            String message = "Here are the tasks in your list:\n";
            int num = 1;
            for (Task task : tasks) {
                message += (num + "." + task + "\n");
                num++;
            }
            return message;
        }
    }

    /**
     * Returns a String message informing the user
     * of the Task that was marked as done.
     *
     * @param task the Task that was marked as done.
     * @return String message of the Task marked as done.
     */
    public String getTaskDoneConfirmation(Task task) {
        return "Nice! I've marked this task as done:\n" + task
                + "\n";
    }

    /**
     * Returns a String message informing the user
     * of the Task that was deleted from the TaskList
     * along with the remaining number of Tasks in that TaskList.
     *
     * @param taskList the TaskList from which the Task was deleted.
     * @param task     the Task to be deleted.
     * @return String message of deleted Task.
     */
    public String getTaskDeleteConfirmation(TaskList taskList, Task task) {
        ArrayList<Task> tasks = taskList.getTasks();
        return "Noted! I've removed this task:\n" + task
                + "\nNow you have " + tasks.size()
                + (tasks.size() == 1 ? " task " : " tasks ") + "in the list.\n";
    }

    /**
     * Returns a String message containing the details
     * of all the Tasks in the TaskList,
     * that matched a keyword input by the user,
     * in numerical order.
     *
     * @param taskList the TaskList containing the matched Tasks to be printed.
     * @return String message containing matching Tasks.
     */
    public String getMatchingTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            return "There are no matching tasks in your list. :(\n";
        } else {
            String message = "Here are the matching tasks in your list:\n";
            int num = 1;
            for (Task task : tasks) {
                message += (num + "." + task) + "\n";
                num++;
            }
            return message;
        }
    }

    /**
     * Returns a String message that signals
     * the termination of the Duke program.
     *
     * @return String message that signals termination.
     */
    public String getByeMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns a String message informing the user that
     * user input was invalid along with the error message.
     *
     * @param e the DukeException containing the details of the error.
     * @return String message containing error.
     */
    public String getDukeExceptionMessage(DukeException e) {
        return "Duke has encountered an error:\n" + e.getMessage()
                + "\n";
    }
}
