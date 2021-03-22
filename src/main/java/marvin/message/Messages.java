package marvin.message;

import marvin.task.Task;
import marvin.task.TaskList;

/**
 * Container for user visible messages;
 */
public class Messages {
    public static final String MESSAGE_WELCOME = "Hello! I'm Marvin what can I do for you today?\n";
    public static final String MESSAGE_EXIT = "Bye bye!!!\n";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n";
    public static final String MESSAGE_INVALID_ENCODED_FORMAT = "Task encoded in invalid format";
    public static final String MESSAGE_INVALID_ENCODED_TODO_FORMAT = "Todo encoded in invalid format";
    public static final String MESSAGE_INVALID_ENCODED_DEADLINE_FORMAT = "Deadline encoded in invalid format";
    public static final String MESSAGE_INVALID_ENCODED_EVENT_FORMAT = "Event encoded in invalid format";
    private static final String MESSAGE_ADD_TASK =
            "Got it I've added this task:\n" + "%s\n" + "You now have %d tasks in the list\n";
    private static final String MESSAGE_DELETE_TASK =
            "Noted I've removed this task:\n" + "%s\n" + "You now have %d tasks in the list\n";
    private static final String MESSAGE_DONE_TASK = "Nice! I've marked this task as done:\n" + "%s";
    private static final String MESSAGE_NO_TASKS = "You currently have no tasks added!\n";
    private static final String MESSAGE_LIST_TASKS = "Here is a list of all your tasks:\n" + "%s";
    private static final String MESSAGE_UNABLE_TO_FIND_TASKS = "No found tasks matching your query.\n";
    private static final String MESSAGE_FIND_TASKS = "Here are the matching tasks in your list:\n" + "%s";

    /**
     * Returns the message to be shown to the user when a task has been added to the task list.
     * @param task The task added to the task list.
     * @param numberOfTasks The updated number of tasks in the task list.
     * @return Message to be shown to user.
     */
    public static String getAddTaskMessage(Task task, int numberOfTasks) {
        return String.format(MESSAGE_ADD_TASK, task, numberOfTasks);
    }

    /**
     * Returns the message to be shown to the user when a task has been deleted from the task list.
     * @param task The task deleted from the task list.
     * @param numberOfTasks The updated number of tasks in the task list.
     * @return Message to be shown to user.
     */
    public static String getDeleteTaskMessage(Task task, int numberOfTasks) {
        return String.format(MESSAGE_DELETE_TASK, task, numberOfTasks);
    }

    /**
     * Returns the message to be shown to the user when a task has been marked done in the task list.
     * @param task The task marked done.
     * @return Message to be shown to user.
     */
    public static String getDoneTaskMessage(Task task) {
        return String.format(MESSAGE_DONE_TASK, task);
    }

    /**
     * Returns the message to be shown to the user when a request to list all tasks in the task list is made.
     * If no task are in the task list, the appropriate message is also shown to the user.
     * @param tasks The task list.
     * @return Message to be shown.
     */
    public static String getListTaskMessage(TaskList tasks) {
        int numberOfTasks = tasks.size();
        if (numberOfTasks == 0) {
            return MESSAGE_NO_TASKS;
        }

        return String.format(MESSAGE_LIST_TASKS, tasks);
    }

    /**
     * Returns the message to be shown to to the user when a request to find tasks in the task list is made.
     * If no matching tasks are found in the task list, the appropriate message is also shown to the user.
     * @param foundTasks The matching tasks found in the task list.
     * @return Message to be shown.
     */
    public static String getFindTaskMessage(TaskList foundTasks) {
        int numberOfFoundTasks = foundTasks.size();
        if (numberOfFoundTasks == 0) {
            return MESSAGE_UNABLE_TO_FIND_TASKS;
        }

        return String.format(MESSAGE_FIND_TASKS, foundTasks);
    }
}
