package seedu.io;

import seedu.common.PrintText;
import seedu.task.Task;
import seedu.task.TaskList;

/**
 * Handles UI responses.
 */
public class Ui {

    private String formatResponse(String message) {
        return PrintText.BORDER + message + PrintText.BORDER;
    }

    /**
     * Prints a formatted message.
     *
     * @param text The message to be printed.
     */
    public void showFormatResponse(String text) {
        System.out.print(PrintText.BORDER.toString() + text + PrintText.BORDER.toString());
    }

    /**
     * Prints a formatted message.
     * Overloads previous method.
     *
     * @param text The {@code PrintText} message to be printed.
     */
    public void showFormatResponse(PrintText text) {
        System.out.print(PrintText.BORDER.toString() + text + PrintText.BORDER.toString());
    }

    /**
     * Prints a formatted message after successfully executing a 'list' command.
     *
     * @param taskList The {@code TaskList} with all the tasks.
     */
    public void showList(TaskList taskList) {
        String message = "  Here are all the tasks in your list:\n\n"
                + taskList.toString() + "\n"
                + String.format("  Only %s tasks left to be done!\n",
                taskList.getTotalNumberOfTasksUndone());

        System.out.print(this.formatResponse(message));
    }

    /**
     * Prints a formatted message after successfully executing a 'done' command.
     *
     * @param id The list index of the task marked as "done".
     * @param task The task that was "done".
     * @param totalNumberOfTasksUndone The number of tasks "not done" in the {@code TaskList}.
     */
    public void showDoneSuccess(int id, Task task, int totalNumberOfTasksUndone) {
        String message = "  Great job! You're done with:\n\n"
                + String.format("  %s. %s\n\n", id, task)
                + String.format("  Now %s tasks are left to be done!\n",
                totalNumberOfTasksUndone);

        System.out.print(this.formatResponse(message));
    }

    /**
     * Prints a formatted message after successfully executing a 'delete' command.
     *
     * @param id The list index of the task to be deleted.
     * @param task The task that was deleted.
     * @param totalNumberOfTasks The number of tasks marked as "done" in the {@code TaskList}.
     */
    public void showDeleteSuccess(int id, Task task, int totalNumberOfTasks) {
        String message = "  Got it, this task is now deleted:\n\n"
                + String.format("  %s. %s\n\n", id, task)
                + String.format("  You now have %s tasks left if your list.\n",
                totalNumberOfTasks);

        System.out.print(this.formatResponse(message));
    }

    /**
     * Prints a formatted message after successfully executing
     * a 'todo', 'deadline' or 'event' command.
     *
     * @param totalNumberOfTasks The number of tasks marked as "done" in the {@code TaskList}.
     * @param newTask The task that was added.
     * @param totalNumberOfTasksUndone The number of tasks marked "not done" in the {@code TaskList}.
     */
    public void showNewTaskAddedSuccess(int totalNumberOfTasks, Task newTask, int totalNumberOfTasksUndone) {
        String message = "  Okie added new task:\n\n"
                + String.format("  %s. %s\n\n", totalNumberOfTasks, newTask)
                + String.format("  Total %s tasks, only %s left to be done!\n",
                totalNumberOfTasks,
                totalNumberOfTasksUndone);
        
        System.out.print(this.formatResponse(message));
    }

    /**
     * Prints a formatted error message for 'todo', 'deadline' or 'event' commands.
     * Message shows the expected input format for the respective commands.
     *
     * @param format The {@code PrintText} format message to be printed.
     */
    public void showFormatError(PrintText format) {
        String message = "  Oops! Please say it like this:\n\n" + format;
        System.out.print(this.formatResponse(message));
    }

    /**
     * Prints a formatted error message for 'done' or 'delete' commands.
     * Message shows the expected input format for the respective commands.
     */
    public void showInvalidIndexError() {
        String message = "  Oops! Please use a valid task number.\n\n"
                + "  Type 'list' to view all tasks\n  and their respective numbers\n";
        System.out.print(this.formatResponse(message));
    }

    /**
     * Prints a formatted error message when starting the application with an empty tasklist.
     */
    public void showLoadingError() {
        String message = "  No existing data found. Starting with empty task.Task list.\n";
        System.out.print(this.formatResponse(message));
    }
}
