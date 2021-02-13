package duke.message;

import duke.task.Task;
import duke.task.TaskList;

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

    public static String getAddTaskMessage(Task task, int numberOfTasks) {
        return String.format(MESSAGE_ADD_TASK, task, numberOfTasks);
    }

    public static String getDeleteTaskMessage(Task task, int numberOfTasks) {
        return String.format(MESSAGE_DELETE_TASK, task, numberOfTasks);
    }

    public static String getDoneTaskMessage(Task task) {
        return String.format(MESSAGE_DONE_TASK, task);
    }

    public static String getListTaskMessage(TaskList tasks) {
        int numberOfTasks = tasks.size();
        if (numberOfTasks == 0) {
            return MESSAGE_NO_TASKS;
        }

        return String.format(MESSAGE_LIST_TASKS, tasks);
    }

    public static String getFindTaskMessage(TaskList foundTasks) {
        int numberOfFoundTasks = foundTasks.size();
        if (numberOfFoundTasks == 0) {
            return MESSAGE_UNABLE_TO_FIND_TASKS;
        }

        return String.format(MESSAGE_FIND_TASKS, foundTasks);
    }
}
