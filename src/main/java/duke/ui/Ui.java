package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Handles Ui related functions such as input and output.
 */
public class Ui {
    /**
     * Message shown when user opens the application
     */
    public static final String WELCOME_MESSAGE = "Hello! I'm Chip the Squirrel! How can I help you today?";
    /**
     * Message shown when user closes the application
     */
    public static final String GOODBYE_MESSAGE = "Bye! Hope to see you again soon!";

    private static String joinStringsWithNewLines(String... strings) {
        return String.join("\n", strings);
    }

    /**
     * Returns a message to be shown after a task is successfully marked as done.
     *
     * @param task task to be marked as done.
     * @return Message shown after task is successfully marked as done.
     */
    public static String getSuccessfullyDoneMessage(Task task) {
        assert task != null : "task should not be null";
        return joinStringsWithNewLines("Good Job! I've marked this task as done!",
            task.toString());
    }

    /**
     * Returns a message to be shown after a task is successfully deleted.
     *
     * @param numTasks Number of tasks left in the list.
     * @param task     Task that was deleted.
     * @return Message shown after task is successfully deleted.
     */
    public static String getSuccessfullyDeletedMessage(int numTasks, Task task) {
        String formattedTasksCount = numTasks > 1 ? String.format("%d tasks", numTasks) : "1 task";
        assert task != null : "task should not be null";
        return joinStringsWithNewLines("Got it! I've removed this task:",
            "  " + task.toString(),
            "Now you have " + formattedTasksCount + " in the list.");
    }

    /**
     * Returns a string representation of list of tasks.
     * Tasks should already be filtered by search command.
     *
     * @param tasks List of tasks.
     * @return string representation of list of tasks.
     */
    public static String getFilteredTasksMessage(ArrayList<Task> tasks) {
        assert tasks != null : "tasks should not be null";
        if (tasks.size() == 0) {
            return "No matching tasks were found.";
        } else {
            String[] outputArr = new String[tasks.size() + 1];
            outputArr[0] = "Here are the matching tasks in your list:";

            for (int i = 0; i < tasks.size(); i++) {
                outputArr[i + 1] = (i + 1) + "." + tasks.get(i).toString();
            }

            return joinStringsWithNewLines(outputArr);
        }
    }

    /**
     * Returns a string representation of list of all tasks.
     *
     * @param tasks List of tasks.
     * @return string representation of list of tasks.
     */
    public static String getAllTasksMessage(ArrayList<Task> tasks) {
        assert tasks != null : "tasks should not be null";
        if (tasks.size() == 0) {
            return "You have not added any tasks.";
        } else {
            String[] tasksArr = new String[tasks.size()];

            for (int i = 0; i < tasks.size(); i++) {
                tasksArr[i] = (i + 1) + "." + tasks.get(i).toString();
            }

            return joinStringsWithNewLines(tasksArr);
        }
    }

    public static String getSuccessfullyAddedTaskMessage(int numTasks, Task task) {
        String formattedTasksCount = numTasks > 1 ? String.format("%d tasks", numTasks) : "1 task";
        assert task != null : "task should not be null";
        return joinStringsWithNewLines("Got it! I've added this task:",
            "  " + task.toString(),
            "Now you have " + formattedTasksCount + " in the list.");
    }
}
