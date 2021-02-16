package duke.ui;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Manages the building and formatting of the status strings to be displayed in the GUI.
 */
public class TaskStringFormatter {
    private static final int targetDescLength = 20;

    /**
     * Computes a pretty task table made of strings, with headers and separators, to be displayed
     * in the application's GUI. This table will contain only 1 task.
     *
     * @param task <code>Task</code> to be displayed in the output task table.
     * @return A pretty task table.
     */
    public static String getTaskTable(Task task) {
        assert (task instanceof ToDo || task instanceof Deadline || task instanceof Event);
        String tableHeader = getTaskTableHeader(false);
        return tableHeader + getStatusString(task);
    }

    /**
     * Computes a pretty task table made of strings, with headers and separators, to be displayed
     * in the application's GUI. This table will contain a list of tasks.
     *
     * @param tasks A <code>TaskList</code>, the elements of which are to be displayed in the
     *              output task table.
     * @return A pretty task table.
     */
    public static String getTaskTable(TaskList tasks) {
        String tableHeader = getTaskTableHeader(true);

        StringBuilder output = new StringBuilder(tableHeader);
        for (int index = 0; index < tasks.getSize(); index++) {
            int taskNumber = index + 1;
            Task task = tasks.getTaskByIndex(taskNumber);
            assert (task instanceof ToDo || task instanceof Deadline || task instanceof Event);
            output.append(taskNumber).append(".").append(getStatusString(task)).append("\n");
        }

        return output.toString();
    }

    /**
     * Computes a status string for the given <code>Task</code>, containing the following information:
     * 1. Status
     * 2. Description
     * 3. Time (if applicable)
     *
     * @param task <code>Task</code>> for which the status string is to be computed.
     * @return The task's status string.
     */
    private static String getStatusString(Task task) {
        assert (task instanceof ToDo || task instanceof Deadline || task instanceof Event);

        String description = task.getDescription();
        if (description.length() < targetDescLength) {
            description = String.format("%1$-" + targetDescLength + "s", description);
        } else if (description.length() > targetDescLength) {
            description = description.substring(0, targetDescLength - 3) + "...";
        }

        String status = "[" + (task.isDone() ? "X" : " ") + "]";
        String time = "";
        if (task instanceof ToDo) {
            status = "[T]" + status;
        } else if (task instanceof Deadline) {
            status = "[D]" + status;
            time = ((Deadline) task).getByDateTimeString();
        } else {
            status = "[E]" + status;
            time = ((Event) task).getAtDateTimeString();
        }
        return status + " | " + description + " | " + time;
    }

    /**
     * Obtains a pretty table header with separators and the following three columns:
     * 1. Status
     * 2. Description
     * 3. Time
     *
     * @param isList <code>Boolean</code> indicating whether the constituent of the resulting
     *               task table is a list of tasks, or just one task.
     * @return A pretty table header.
     */
    private static String getTaskTableHeader(boolean isList) {
        String statusColHeader = isList ? "Status  " : "Status";
        String descriptionColHeader = String.format("%1$-" + targetDescLength + "s", "Description");
        String timeColHeader = "Time            ";
        String header = statusColHeader + " | " + descriptionColHeader + " | " + timeColHeader;
        String separator = "-".repeat(header.length());

        return header + "\n" + separator + "\n";
    }
}
