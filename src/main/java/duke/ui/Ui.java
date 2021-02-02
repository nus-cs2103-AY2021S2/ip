package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private static final String NEW_LINE = "\n";
    private static final String GREETING_MESSAGE =
            "Hi there! I am Moomin" + NEW_LINE + "What can I do for you today?";
    private static final String FAREWELL_MESSAGE = "Goodbye. Have a nice day!!";
    private static final String ERROR_START = "Hmm... ";
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String TASK_COMPLETED_MESSAGE = "Congratulations! You have completed this task:";
    private static final String TASK_ALR_COMPLETED_MESSAGE = "You have already completed this task:";
    private static final String TASK_REMOVED_MESSAGE = "Noted. This task has been removed:";
    private static final String EMPTY_LIST_MESSAGE = "It seems like there is nothing in your list.";
    private static final String PRINT_LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String NO_FOUND_TASK_MESSAGE =
            "It seems like there is no task matching your keyword:";
    private static final String PRINT_FOUND_TASKS_MESSAGE = "Here are the matching tasks in your list:";

    /**
     * Gets the greeting message.
     *
     * @return Greeting message.
     */
    public String getGreetingMessage() {
        return GREETING_MESSAGE;
    }

    /**
     * Gets the farewell message.
     *
     * @return Farewell message.
     */
    public String getFarewellMessage() {
        return FAREWELL_MESSAGE;
    }

    /**
     * Gets the error message.
     *
     * @return Formatted error message.
     */
    public String getErrorMessage(String message) {
        return ERROR_START + message;
    }

    /**
     * Gets a feedback message after user adds a task.
     *
     * @param task  Task added by user.
     * @param tasks List of tasks the task was added to.
     * @return Add task report.
     */
    public String getAddTaskReport(Task task, TaskList tasks) {
        StringBuilder response = new StringBuilder();
        response.append(TASK_ADDED_MESSAGE + NEW_LINE);
        response.append(getTaskInfo(task) + NEW_LINE);
        response.append(getTaskCountInfo(tasks) + NEW_LINE);
        return response.toString();
    }

    /**
     * Gets a message containing the total number of tasks.
     *
     * @param tasks List of all the tasks.
     * @return Task count info.
     */
    public String getTaskCountInfo(TaskList tasks) {
        return "Now you have " + tasks.getTaskCount() + " in the list.";
    }

    /**
     * Gets a feedback message after user marks a task as done.
     *
     * @param task    Task marked as done.
     * @param wasDone If the task was already marked as done.
     * @return Mark task as done feedback message.
     */
    public String getMarkTaskAsDoneMessage(Task task, boolean wasDone) {
        StringBuilder response = new StringBuilder();
        if (wasDone) {
            response.append(TASK_ALR_COMPLETED_MESSAGE + NEW_LINE);
        } else {
            response.append(TASK_COMPLETED_MESSAGE + NEW_LINE);
        }
        response.append(getTaskInfo(task));
        return response.toString();
    }

    /**
     * Gets a feedback message after user deletes a task.
     *
     * @param task  Task deleted by user.
     * @param tasks List of tasks the task was deleted from.
     * @return Delete task message.
     */
    public String getDeleteTaskMessage(Task task, TaskList tasks) {
        StringBuilder response = new StringBuilder();
        response.append(TASK_REMOVED_MESSAGE + NEW_LINE);
        response.append(getTaskInfo(task) + NEW_LINE);
        response.append(getTaskCountInfo(tasks));
        return response.toString();

    }

    /**
     * Gets a task after formatting.
     *
     * @param task Task to be displayed.
     * @return Task info message.
     */
    public String getTaskInfo(Task task) {
        return task.toString();
    }

    /**
     * Gets all the tasks after formatting.
     *
     * @param tasks List of all the tasks.
     * @return Info of al tasks after formatting
     */
    public String getAllTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            return EMPTY_LIST_MESSAGE;
        } else {
            StringBuilder response = new StringBuilder();
            response.append(PRINT_LIST_MESSAGE + NEW_LINE);
            appendTaskToString(tasks, response);
            return response.toString();
        }
    }

    /**
     * gets all the tasks found by a keyword after formatting.
     *
     * @param tasks List of found tasks.
     * @return Info of all found tasks after formatting
     */
    public String getAllFoundTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            return NO_FOUND_TASK_MESSAGE;
        } else {
            StringBuilder response = new StringBuilder();
            response.append(PRINT_FOUND_TASKS_MESSAGE + NEW_LINE);
            appendTaskToString(tasks, response);
            return response.toString();
        }
    }

    /**
     * Adds info string of all the tasks in the given TaskList
     * to the provided StringBuilder
     *
     * @param tasks TaskList to be printed.
     * @param sb    StringBuilder which task string will be appended to
     */
    public void appendTaskToString(TaskList tasks, StringBuilder sb) {
        for (int i = 1; i <= tasks.getTaskCount(); ++i) {
            sb.append(i + "." + tasks.getTask(i - 1).toString() + NEW_LINE);
        }
    }
}
