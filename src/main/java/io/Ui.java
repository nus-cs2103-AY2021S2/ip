package io;

import common.PrintText;
import task.Task;

public class Ui {

    public String formatResponse(String message) {
        return PrintText.BORDER + message + PrintText.BORDER;
    }

    public void showFormatResponse(String text) {
        System.out.print(PrintText.BORDER.toString() + text + PrintText.BORDER.toString());
    }

    public void showFormatResponse(PrintText text) {
        System.out.print(PrintText.BORDER.toString() + text + PrintText.BORDER.toString());
    }

    public void showDoneSuccess(int id, Task task, int totalNumberOfTasksUndone) {
        String message = "  Great job! You're done with:\n\n"
                + String.format("  %s. %s\n\n", id, task)
                + String.format("  Now %s tasks are left to be done!\n", totalNumberOfTasksUndone);
        System.out.print(this.formatResponse(message));
    }

    public void showDeleteSuccess(int id, Task task, int totalNumberOfTasks) {
        String message = "  Got it, this task is now deleted:\n\n"
                + String.format("  %s. %s\n\n", id, task)
                + String.format("  You now have %s tasks left if your list.\n", totalNumberOfTasks);
        System.out.print(this.formatResponse(message));
    }

    public void showNewTaskAddedSuccess(int totalNumberOfTasks, Task newTask, int totalNumberOfTasksUndone) {
        String message = "  Okie added new task:\n\n"
                + String.format("  %s. %s\n\n", totalNumberOfTasks, newTask)
                + String.format("  Total %s tasks, only %s left to be done!\n",
                totalNumberOfTasks,
                totalNumberOfTasksUndone);
        System.out.print(this.formatResponse(message));
    }

    public void showFormatError(PrintText format) {
        String message = "  Oops! Please say it like this:\n\n" + format;
        System.out.print(this.formatResponse(message));
    }

    // only for delete and done commands
    public void showInvalidIndexError() {
        String message = "  Oops! Please use a valid task number.\n\n"
                + "  Type 'list' to view all tasks\n  and their respective numbers\n";
        System.out.print(this.formatResponse(message));
    }

    public void showLoadingError() {
        String message = "  No existing data found. Starting with empty task.Task list.\n";
        System.out.print(this.formatResponse(message));
    }
}
