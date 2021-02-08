package duke.ui;

import java.util.List;

import duke.duke.Duke;
import duke.tasks.Task;

/**
 * Manages the UI component of the application.
 */
public class Ui {

    /**
     * Displays welcome message upon launch of application.
     * @param bot A Duke object that manages task list operations.
     */
    public static String showWelcomeMessage(Duke bot) {
        return String.format("Hello! I'm Duke\n%s\n%s\n", bot.getLogo(), "What can I do for you?");
    }

    /**
     * Displays response for ListCommand.
     * @param list List of task to be printed.
     */
    public static String showTasksToUser(List<Task> list) {
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            tasks.append(String.format("%d.%s%n", i + 1, list.get(i)));
        }
        return tasks.toString();
    }

    /**
     * Displays response for a task type Command.
     * @param task The new task added.
     * @param list List of tasks.
     */
    public static String showAddTaskMessage(Task task, List<Task> list) {
        String taskMessage;
        taskMessage = "Got it. I've added this task:\n" + String.format(" %s%n", task)
                + String.format("Now you have %d tasks in the list.%n", list.size());
        return taskMessage;
    }

    /**
     * Displays response for DeleteCommand.
     * @param task The task to be removed.
     * @param list List of tasks.
     */
    public static String showRemoveTaskMessage(Task task, List<Task> list) {
        return "Noted. I've removed this task:" + String.format(" %s%n", task)
                + String.format("Now you have %d tasks in the list.%n", list.size());
    }

    /**
     * Displays response for DoneCommand.
     * @param list List of tasks.
     * @param id Index of task to be marked as done.
     */
    public static String showDoneTaskMessage(List<Task> list, int id) {
        return "Nice! I've marked this task as done:"
                + String.format(" %s%n", list.get(id));
    }

    /**
     * Displays response for ByeCommand.
     */
    public static String showExitMessage() {
        return "Bye. Hope to see you again soon!";
    }
}
