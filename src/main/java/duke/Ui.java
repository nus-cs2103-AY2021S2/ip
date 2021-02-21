package duke;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {

    private static final String NEWLINE = "\n";
    private static final String TAB = "\t";
    private static final String SPACE = " ";
    private static final String PERIOD = ".";
    private static final String DOUBLE_QUOTE = "\"";

    private static final String ERROR_HEADING = "Oh no... ";
    private static final String INVALID_COMMAND = "I'm not trained with these commands yet...";
    private static final String GREETING = "Hello there! I'm Duke, your personal chat bot."
            + NEWLINE + "To know more about what I can do, type 'usage'."
            + NEWLINE + "So... Is there anything I can do for you today?";
    private static final String USAGE = "These are the commands available:"
            + NEWLINE + TAB + "- usage"
            + NEWLINE + TAB + "- bye"
            + NEWLINE + TAB + "- list"
            + NEWLINE + TAB + "- find <keyword>"
            + NEWLINE + TAB + "- save"
            + NEWLINE + TAB + "- todo <task_description>"
            + NEWLINE + TAB + "- deadline <task_description> /by <date_time>"
            + NEWLINE + TAB + "- event <task_description> /at <date_time>"
            + NEWLINE + TAB + "- delete <task_number>"
            + NEWLINE + TAB + "- done <task_number>";
    private static final String BYE_SUCCESS = "Alright, take care. Hope to see you again!";
    private static final String STORAGE_LOAD_FAILURE = "Argh, I can't load your data file.";
    private static final String STORAGE_SAVE_SUCCESS = "Alright, I've saved your current data.";
    private static final String STORAGE_SAVE_FAILURE = "Argh, I can't save your data.";
    private static final String TASK_LIST_TITLE = "Tasks currently in your list:";
    private static final String TASK_LIST_EMPTY = "There isn't any task found in the list.";
    private static final String TASK_ADD_SUCCESS = "have been successfully added to the list.";
    private static final String TASK_DELETE_SUCCESS = "have been successfully removed from the list.";
    private static final String TASK_MARK_DONE_SUCCESS = "have been successfully marked as completed.";
    private static final String TASK_MARK_UNDONE_SUCCESS = "have been successfully marked as incomplete.";

    public static String getErrorMessage(String message) {
        return Ui.ERROR_HEADING + message;
    }

    public static String getInvalidCommand() {
        return Ui.INVALID_COMMAND;
    }

    public static String getGreeting() {
        return Ui.GREETING;
    }

    public static String getUsage() {
        return Ui.USAGE;
    }

    public static String getByeSuccess() {
        return Ui.BYE_SUCCESS;
    }

    public static String getLoadTaskListFailure() {
        return Ui.STORAGE_LOAD_FAILURE;
    }

    public static String getSaveTaskListSuccess() {
        return Ui.STORAGE_SAVE_SUCCESS;
    }

    public static String getSaveTaskListFailure() {
        return Ui.STORAGE_SAVE_FAILURE;
    }

    public static String getTaskList(ArrayList<Task> tasks) {
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

    public static String getAddTaskSuccess(Task task) {
        return Ui.DOUBLE_QUOTE + task.getDescription() + Ui.DOUBLE_QUOTE
                + Ui.SPACE + Ui.TASK_ADD_SUCCESS;
    }

    public static String getDeleteTaskSuccess(Task task) {
        return Ui.DOUBLE_QUOTE + task.getDescription() + Ui.DOUBLE_QUOTE
                + Ui.SPACE + Ui.TASK_DELETE_SUCCESS;
    }

    public static String getMarkDoneTaskSuccess(Task task) {
        return Ui.DOUBLE_QUOTE + task.getDescription() + Ui.DOUBLE_QUOTE
                + Ui.SPACE + Ui.TASK_MARK_DONE_SUCCESS;
    }

    public static String getMarkUndoneTaskSuccess(Task task) {
        return Ui.DOUBLE_QUOTE + task.getDescription() + Ui.DOUBLE_QUOTE
                + Ui.SPACE + Ui.TASK_MARK_UNDONE_SUCCESS;
    }
}
