package duke.ui;

import duke.command.Command;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private static final String NEW_LINE = "\n";
    private static final String GREETING_MESSAGE =
            "Hi there! I am Moomin" + NEW_LINE + "What can I do for you today?";
    public static final String FAREWELL_MESSAGE = "Goodbye. Have a nice day!!";
    private static final String ERROR_START = "Hmm... ";
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    private static final String UNDO_TASK_ADDED_MESSAGE = "Got it. I've removed this task:";
    private static final String TASK_COMPLETED_MESSAGE = "Congratulations! You have completed this task:";
    private static final String UNDO_TASK_COMPLETED_MESSAGE = "Okay! I have marked this task as not done:";
    private static final String TASK_ALR_COMPLETED_MESSAGE = "You have already completed this task:";
    private static final String TASK_REMOVED_MESSAGE = "Noted. This task has been removed:";
    private static final String UNDO_TASK_REMOVED_MESSAGE = "Noted. This task has been added back:";
    private static final String EMPTY_LIST_MESSAGE = "It seems like there is nothing in your list.";
    private static final String PRINT_LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String UNDO_LIST_MESSAGE = "You wanna see the list again?";
    private static final String NO_FOUND_TASK_MESSAGE =
            "It seems like there is no task matching your keyword:";
    private static final String PRINT_FOUND_TASKS_MESSAGE = "Here are the matching tasks in your list:";
    private static final String UNDO_FOUND_TASKS_MESSAGE = "Hmm.. How do I unfind the tasks?";
    private static final String UNDO_UNDO_MESSAGE = "Hmm.. How do I undo an undo?";
    private static final String NO_UNDO_MESSAGE = "I can't find any commands to undo.";

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
     * Gets a feedback message after user undos an undo task.
     *
     * @return Undo message.
     */
    public String getUndoUndoMessage() {
        return UNDO_UNDO_MESSAGE;
    }

    /**
     * Gets a feedback message when there is no command to undo.
     *
     * @return Undo message.
     */
    public String getNoUndoMessage() {
        return NO_UNDO_MESSAGE;
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
     * Combines messages to a single string.
     *
     * @return Combined message.
     */
    public String combineMessages(String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message + NEW_LINE);
        }
        return sb.toString();
    }

    /**
     * Gets a feedback message after user adds a task.
     *
     * @param task  Task added by user.
     * @param tasks List of tasks the task was added to.
     * @return Add task report.
     */
    public String getAddTaskReport(Task task, TaskList tasks) {
        return combineMessages(TASK_ADDED_MESSAGE, getTaskInfo(task), getTaskCountInfo(tasks));
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
     * Gets a feedback message after user undos an add task command.
     *
     * @param task  Task added by user.
     * @param tasks List of tasks the task was added to.
     * @return Undo message.
     */
    public String getUndoAddTaskMessage(Task task, TaskList tasks) {
        return combineMessages(UNDO_TASK_ADDED_MESSAGE, getTaskInfo(task), getTaskCountInfo(tasks));
    }

    /**
     * Gets a feedback message after user marks a task as done.
     *
     * @param task    Task marked as done.
     * @param wasDone If the task was already marked as done.
     * @return Mark task as done feedback message.
     */
    public String getMarkTaskAsDoneMessage(Task task, boolean wasDone) {
        String taskCompleteMessage = wasDone ? TASK_ALR_COMPLETED_MESSAGE : TASK_COMPLETED_MESSAGE;
        return combineMessages(taskCompleteMessage, getTaskInfo(task));
    }

    /**
     * Gets a feedback message after user undos an mark as done command.
     *
     * @param task Task marked as done.
     * @return Undo message.
     */
    public String getUndoMarkAsDoneMessage(Task task) {
        return combineMessages(UNDO_TASK_COMPLETED_MESSAGE, getTaskInfo(task));
    }

    /**
     * Gets a feedback message after user deletes a task.
     *
     * @param task  Task deleted by user.
     * @param tasks List of tasks the task was deleted from.
     * @return Delete task message.
     */
    public String getDeleteTaskMessage(Task task, TaskList tasks) {
        return combineMessages(TASK_REMOVED_MESSAGE, getTaskInfo(task), getTaskCountInfo(tasks));
    }

    /**
     * Gets a feedback message after user undos an delete task command.
     *
     * @param task  Task deleted by user.
     * @param tasks List of tasks the task was deleted from.
     * @return Undo message.
     */
    public String getUndoDeleteTaskMessage(Task task, TaskList tasks) {
        return combineMessages(UNDO_TASK_REMOVED_MESSAGE, getTaskInfo(task), getTaskCountInfo(tasks));
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
        }
        return combineMessages(PRINT_LIST_MESSAGE, combineTasksToString(tasks));
    }

    /**
     * Gets a feedback message after user undos a list task command.
     *
     * @return Undo message.
     */
    public String getUndoListTaskMessage() {
        return combineMessages(UNDO_LIST_MESSAGE);
    }

    /**
     * Gets all the tasks found by a keyword after formatting.
     *
     * @param tasks List of found tasks.
     * @return Info of all found tasks after formatting
     */
    public String getAllFoundTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            return NO_FOUND_TASK_MESSAGE;
        }
        return combineMessages(PRINT_FOUND_TASKS_MESSAGE, combineTasksToString(tasks));
    }

    /**
     * Gets a feedback message after user undos a find task command.
     *
     * @return Undo message.
     */
    public String getUndoFindTaskMessage() {
        return combineMessages(UNDO_FOUND_TASKS_MESSAGE);
    }

    /**
     * Combine info strings of all the tasks in the given TaskList into one.
     *
     * @param tasks TaskList to be printed.
     * @return Combines string containing all tasks in TaskList.
     */
    public String combineTasksToString(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tasks.getTaskCount(); ++i) {
            sb.append(i + "." + tasks.getTask(i - 1).toString() + NEW_LINE);
        }
        return sb.toString();
    }
}
