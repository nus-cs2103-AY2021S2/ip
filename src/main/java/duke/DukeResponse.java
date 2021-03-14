package duke;

import exception.DukeEmptyTaskListException;
import exception.DukeException;
import misc.Color;
import misc.Emoji;
import task.Task;

/**
 * Duke response object.
 */
public class DukeResponse {
    /**
     * Duke task add headers.
     */
    public static final String TASK_ADD_HEADER = "Alrighty! I've added this task:\n";
    /**
     * Duke task delete headers.
     */
    public static final String TASK_DELETE_HEADER = "Gotcha! I've removed this task:\n";
    /**
     * Duke task footer1.
     */
    public static final String TASK_FOOTER1 = "\nNow you have ";
    /**
     * Duke task footer2.
     */
    public static final String TASK_FOOTER2 = " tasks in the list.";
    /**
     * Last output message.
     */
    protected String currentMessage;

    /**
     * Instantiates a new Duke response.
     */
    public DukeResponse() {
        welcome();
    }

    /**
     * Respond a welcome message to the user.
     */
    public String welcome() {
        this.currentMessage = "Alright Butters!\n"
                + "What do you want to do today?";
        return this.currentMessage;
    }

    /**
     * Respond a farewell message to the user.
     */
    public String farewell() {
        this.currentMessage = "Bye bye Butters! See you tomorrow!";
        return this.currentMessage;
    }

    /**
     * Acknowledges user with added task message.
     *
     * @param task  the task
     * @param count the count
     */
    public String addTask(Task task, int count) {
        this.currentMessage = TASK_ADD_HEADER + Emoji.TASK + " "
                + task + TASK_FOOTER1 + count + TASK_FOOTER2;
        return printMessage();
    }

    /**
     * Acknowledges user with deleted task message.
     *
     * @param task  the task
     * @param count the count
     */
    public String deleteTask(Task task, int count) {
        this.currentMessage = TASK_DELETE_HEADER
                + Emoji.TRASH + " " + task
                + TASK_FOOTER1 + count + TASK_FOOTER2;
        return printMessage();
    }

    /**
     * Respond with a task marked done message.
     *
     * @param task the task
     */
    public String markAsDone(Task task) {
        this.currentMessage = "Sweet! I've marked this task as done:\n"
                + task.toString();
        return printMessage();
    }

    /**
     * Output the current task list.
     *
     * @param list the list
     */
    public String listTasks(DukeTaskList list, boolean isFind) throws DukeException {
        if (list.size() == 0) {
            if (isFind) {
                throw new DukeException("Woops! No task found under those parameters!");
            } else {
                throw new DukeEmptyTaskListException();
            }

        }
        StringBuilder strList = new StringBuilder();
        list.taskList.stream().forEach((t) -> strList
                .append(list.taskList.indexOf(t) + 1)
                .append(" \u00BB ")
                .append(t.toString())
                .append("\n"));
        if (isFind) {
            this.currentMessage = "Oh oh! I've found something:\n" + strList.substring(0,
                    strList.toString().length() - 1);
        } else {
            this.currentMessage = "Hey Butters! You've got to do these tasks:\n" + strList.substring(0,
                    strList.toString().length() - 1);
        }
        return printMessage();
    }


    /**
     * Print message.
     */
    public String printMessage() {
        return this.currentMessage;
    }

}
