package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents the UI of the Duke chat bot.
 * It manages all the IO operations.
 */
public class Ui {

    private static final String NEWLINE = "\n";
    private static final String TAB = "\t";
    private static final String SPACE = " ";
    private static final String PERIOD = ".";

    private static final String GREETING = "Hello there! I'm Duke, your personal chat bot."
            + NEWLINE + "To know more about what I can do, type 'usage'."
            + NEWLINE + "So... Is there anything I can do for you today?";
    private static final String USAGE = "These are the commands available:"
            + NEWLINE + TAB + "- usage"
            + NEWLINE + TAB + "- list"
            + NEWLINE + TAB + "- todo <task_description>"
            + NEWLINE + TAB + "- deadline <task_description> /by <date_time>"
            + NEWLINE + TAB + "- event <task_description> /at <date_time>"
            + NEWLINE + TAB + "- done <task_number>"
            + NEWLINE + TAB + "- delete <task_number>"
            + NEWLINE + TAB + "- save"
            + NEWLINE + TAB + "- bye";
    private static final String NO_TASKS = "There isn't any task found in the list.";
    private static final String LOAD_ERROR = "Unable to load file.";

    /**
     * Retrieves greeting.
     */
    public String getGreeting() {
        return Ui.GREETING;
    }

    /**
     * Retrieves usage.
     */
    public String getUsage() {
        return Ui.USAGE;
    }

    /**
     * Retrieves file load error.
     */
    public String getLoadingError() {
        return Ui.LOAD_ERROR;
    }

    /**
     * Retrieves tasks in list.
     *
     * @param tasks Task list.
     * @return Formatted String of task list.
     */
    public String getTaskList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return Ui.NO_TASKS;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(Ui.TAB);
            sb.append(i);
            sb.append(Ui.PERIOD);
            sb.append(Ui.SPACE);
            sb.append(tasks.get(i));
            sb.append(Ui.NEWLINE);
        }
        return sb.toString();
    }
}
