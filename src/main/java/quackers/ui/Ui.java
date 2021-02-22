package quackers.ui;

import quackers.task.Task;
import quackers.tasklist.TaskList;

/**
 * Represents the Ui capabilities of Quackers.
 */
public class Ui {

    private static final String NEWLINE = "\n";
    private static final String TAB = "\t";
    private static final String SPACE = " ";
    private static final String PERIOD = ".";
    private static final String DOUBLE_QUOTE = "\"";
    private static final String ERROR_HEADING = "Oh no... ";
    private static final String INVALID_COMMAND = "I'm not trained with these commands yet...";
    private static final String GREETING = "Quack quack! I'm Quackers, a duck really good with task management!"
            + NEWLINE + "To know more about what I can do for you, quack 'usage'."
            + NEWLINE + "Is there anything I can do for you today?";
    private static final String USAGE = "These are the commands available:"
            + NEWLINE + TAB + "- usage"
            + NEWLINE + TAB + "- bye"
            + NEWLINE + TAB + "- list"
            + NEWLINE + TAB + "- find <keyword>"
            + NEWLINE + TAB + "- stats"
            + NEWLINE + TAB + "- todo <task_description>"
            + NEWLINE + TAB + "- deadline <task_description> /by <date_time>"
            + NEWLINE + TAB + "- event <task_description> /at <date_time>"
            + NEWLINE + TAB + "- delete <task_number>"
            + NEWLINE + TAB + "- done <task_number>";
    private static final String BYE_SUCCESS = "Alright, take care. Hope to see you again!";
    private static final String STATISTICS_SUCCESS = "";
    private static final String STORAGE_LOAD_FAILURE = "Argh, I can't load your data file.";
    private static final String STORAGE_SAVE_FAILURE = "Argh, I can't save your data."
            + NEWLINE + "I believe you do not have the necessary data directory set up."
            + NEWLINE + "I've taken the liberty to set them up for you. Please try saving again..."
            + NEWLINE + "Quack quack...";
    private static final String TASK_LIST_TITLE = "Tasks currently in your list:";
    private static final String TASK_LIST_EMPTY = "There isn't any task found in the list.";
    private static final String TASK_ADD_SUCCESS = "has been successfully added to the list.";
    private static final String TASK_DELETE_SUCCESS = "has been successfully removed from the list.";
    private static final String TASK_DELETE_FAILURE = "I can't remove something that doesn't even exist...";
    private static final String TASK_MARK_DONE_SUCCESS = "has been successfully marked as completed.";
    private static final String TASK_MARK_DONE_FAILURE = "I can't mark something that doesn't even exist...";
    private static final String TASK_MARK_UNDONE_SUCCESS = "has been successfully marked as incomplete.";
    private static final String TASK_MARK_UNDONE_FAILURE = "I can't mark something that doesn't even exist...";

    /**
     * Formats any given message into an error message.
     *
     * @param message Message input.
     * @return Error message.
     */
    public static String getErrorMessage(String message) {
        return Ui.ERROR_HEADING + message;
    }

    /**
     * Retrieves the invalid command message
     *
     * @return Invalid command message.
     */
    public static String getInvalidCommand() {
        return Ui.INVALID_COMMAND;
    }

    /**
     * Retrieves the greeting.
     *
     * @return Greeting message.
     */
    public static String getGreeting() {
        return Ui.GREETING;
    }

    /**
     * Retrieves the UsageCommand message.
     *
     * @return UsageCommand message.
     */
    public static String getUsage() {
        return Ui.USAGE;
    }

    /**
     * Retrieves the ByeCommand successful message.
     *
     * @return ByeCommand successful message.
     */
    public static String getByeSuccess() {
        return Ui.BYE_SUCCESS;
    }

    /**
     * Retrieves the StatsCommand successful message.
     *
     * @return StatsCommand successful message
     */
    public static String getStatsSuccess() {
        return Ui.STATISTICS_SUCCESS;
    }

    /**
     * Retrieves the message when reading file data failed.
     *
     * @return Reading file data failure message.
     */
    public static String getLoadTaskListFailure() {
        return Ui.STORAGE_LOAD_FAILURE;
    }

    /**
     * Retrieves the message when writing file data failed.
     *
     * @return Writing file data failure message.
     */
    public static String getSaveTaskListFailure() {
        return Ui.STORAGE_SAVE_FAILURE;
    }

    /**
     * Generates a formatted text from a list of tasks.
     * @param tasks List of tasks.
     * @return Formatted text.
     */
    public static String getTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            return Ui.TASK_LIST_EMPTY;
        }

        StringBuilder sb = new StringBuilder(Ui.TASK_LIST_TITLE + Ui.NEWLINE);
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(Ui.TAB);
            sb.append(i + 1);
            sb.append(Ui.PERIOD);
            sb.append(Ui.SPACE);
            sb.append(tasks.get(i));
            sb.append(Ui.NEWLINE);
        }
        return sb.toString();
    }

    /**
     * Retrieves the message when adding task succeeded.
     *
     * @param task Task to add.
     * @return Add task success message.
     */
    public static String getAddTaskSuccess(Task task) {
        return Ui.DOUBLE_QUOTE + task.getDescription() + Ui.DOUBLE_QUOTE + Ui.SPACE + Ui.TASK_ADD_SUCCESS;
    }

    /**
     * Retrieves the message when deleting task succeeded.
     *
     * @param task Task to delete.
     * @return Delete task success message.
     */
    public static String getDeleteTaskSuccess(Task task) {
        return Ui.DOUBLE_QUOTE + task.getDescription() + Ui.DOUBLE_QUOTE + Ui.SPACE + Ui.TASK_DELETE_SUCCESS;
    }

    /**
     * Retrieves the message when deleting task failed.
     *
     * @return Delete task failure message.
     */
    public static String getDeleteTaskFailure() {
        return Ui.TASK_DELETE_FAILURE;
    }

    /**
     * Retrieves the message when marking task as complete succeeded.
     *
     * @param task Task to mark.
     * @return Mark task complete success message.
     */
    public static String getMarkDoneTaskSuccess(Task task) {
        return Ui.DOUBLE_QUOTE + task.getDescription() + Ui.DOUBLE_QUOTE + Ui.SPACE + Ui.TASK_MARK_DONE_SUCCESS;
    }

    /**
     * Retrieves the message when marking task as complete failed.
     *
     * @return Mark task complete fail message.
     */
    public static String getMarkDoneTaskFailure() {
        return Ui.TASK_MARK_DONE_FAILURE;
    }

    /**
     * Retrieves the message when marking task as incomplete succeeded.
     *
     * @param task Task to mark.
     * @return Mark task incomplete success message.
     */
    public static String getMarkUndoneTaskSuccess(Task task) {
        return Ui.DOUBLE_QUOTE + task.getDescription() + Ui.DOUBLE_QUOTE + Ui.SPACE + Ui.TASK_MARK_UNDONE_SUCCESS;
    }

    /**
     * Retrieves the message when marking task as incomplete failed.
     *
     * @return Mark task incomplete fail message.
     */
    public static String getMarkUndoneTaskFailure() {
        return Ui.TASK_MARK_UNDONE_FAILURE;
    }
}
