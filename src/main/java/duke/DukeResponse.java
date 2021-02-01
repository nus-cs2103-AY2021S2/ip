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
     * Duke message dividers.
     */
    public static final String DIVIDER_FRONT = "\n═════════════"
            + "══════════════╣DUKE╠════"
            + "═══════════════════════\n";
    /**
     * Duke message dividers.
     */
    public static final String DIVIDER_BACK = "\n═══════════════"
            + "══════════════════════"
            + "═══════════════════════\n";
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
    public void welcome() {
        this.currentMessage = "Hey there! I'm Duke\n"
                + "What can I do for you today?";
        printMessage();
    }

    /**
     * Respond a farewell message to the user.
     */
    public void farewell() {
        this.currentMessage = "Good bye. Hope to see you again soon!";
        printMessage();
    }

    /**
     * Acknowledges user with added task message.
     *
     * @param task  the task
     * @param count the count
     */
    public void addTask(Task task, int count) {
        this.currentMessage = TASK_ADD_HEADER + Emoji.TASK + " "
                + task + TASK_FOOTER1 + count + TASK_FOOTER2;
        printMessage();
    }

    /**
     * Acknowledges user with deleted task message.
     *
     * @param task  the task
     * @param count the count
     */
    public void deleteTask(Task task, int count) {
        this.currentMessage = TASK_DELETE_HEADER + Color.BLUE_BOLD
                + Emoji.TRASH + Color.RESET + " " + task
                + TASK_FOOTER1 + count + TASK_FOOTER2;
        printMessage();
    }

    /**
     * Respond with a task marked done message.
     *
     * @param task the task
     */
    public void markAsDone(Task task) {
        this.currentMessage = "Sweet! I've marked this task as done:\n"
                + task.toString();
        printMessage();
    }

    /**
     * Output the current task list.
     *
     * @param list the list
     */
    public void listTasks(DukeTaskList list, boolean isFind) throws DukeException {
        if (list.size() == 0) {
            if (isFind) {
                throw new DukeException("Woops! No task found under those parameters!");
            } else {
                throw new DukeEmptyTaskListException();
            }

        }
        StringBuilder strList = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String currStr = (i + 1) + " \u00BB " + task.toString() + "\n";
            strList.append(currStr);
        }
        if (isFind) {
            this.currentMessage = "Found some tasks matching your find:\n" + strList.substring(0,
                    strList.toString().length() - 1);
        } else {
            this.currentMessage = strList.substring(0,
                    strList.toString().length() - 1);
        }
        printMessage();
    }


    /**
     * Print message.
     */
    public void printMessage() {
        System.out.println(DIVIDER_FRONT + this.currentMessage + DIVIDER_BACK);
    }

}
