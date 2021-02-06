package duke.bot;

import java.util.List;

import duke.task.Task;

/** An object that handles the printing of chat bot's messages*/
public class Ui {
    /**
     * Constructs a message
     *
     * @param msg Message to be displayed
     * @return A formatted string containing the constructed message
     */
    public String constructMessage(String msg) {
        return msg + "\n";
    }

    /**
     * Constructs a default welcome message
     *
     * @param botName Name of the chat bot to greet with
     * @return A formatted string containing the constructed welcome message
     */
    public String constructWelcomeMessage(String botName) {
        return constructMessage(String.format("Meow, I'm %s\nWhat can I do for you today?", botName));
    }

    /**
     * Constructs a default exit message
     *
     * @return A formatted string containing the constructed exit message
     */
    public String constructGoodbyeMessage() {
        return constructMessage("Meow. Hope to see you again soon!");
    }

    /**
     * Constructs a message to indicate the addition of a new task
     *
     * @param task Task that was added
     * @param tasksSize Number of total tasks after adding that new task
     * @return A formatted string containing the constructed task addition message
     */
    public String constructAddMessage(Task task, int tasksSize) {
        String msg = "Got it meow. I've added this task:\n" + String.format("  [%s][%s] %s\n", task.getTypeSymbol(),
                task.getStatusSymbol(), task.getDesc()) + String.format("Now you have %d tasks in the list.\n",
                tasksSize);
        return constructMessage(msg);
    }

    /**
     * Constructs a message to indicate the completion of a task
     *
     * @param index Index of the completed task in the list
     * @param task Task that was completed
     * @return A formatted string containing the constructed task completion message
     */
    public String constructDoneMessage(int index, Task task) {
        String msg = "Good job meow, I've marked this task as done:\n" + String.format("%d.[%s][%s] %s\n", index + 1,
                task.getTypeSymbol(), task.getStatusSymbol(), task.getDesc());
        return constructMessage(msg);
    }

    /**
     * Constructs a message to indicate the deletion of a task
     *
     * @param task Task that was deleted
     * @param tasksSize Number of total tasks left after deleting that task
     * @return A formatted string containing the constructed task deletion message
     */
    public String constructDeleteMessage(Task task, int tasksSize) {
        String msg = "Noted meow. I've removed this task:\n" + String.format("  [%s][%s] %s\n", task.getTypeSymbol(),
                task.getStatusSymbol(), task.getDesc()) + String.format("Now you have %d tasks in the list.\n",
                tasksSize);
        return constructMessage(msg);
    }

    /**
     * Constructs a message containing all tasks found from the search
     *
     * @param tasks A list of tasks as search results
     * @return A formatted string containing the constructed search result message
     */
    public String constructFoundMessage(List<Task> tasks) {
        String msg = "Meow, here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            msg += String.format("%d.[%s][%s] %s\n", i + 1, task.getTypeSymbol(), task.getStatusSymbol(),
                    task.getDesc());
        }
        return constructMessage(msg);
    }

    /**
     * Constructs a message containing all tasks found in the list
     *
     * @param tasks List of tasks
     * @return A formatted string containing the constructed task list message
     */
    public String constructTaskListMessage(List<Task> tasks) {
        String msg = "Meow, here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            msg += String.format("%d.[%s][%s] %s\n", i + 1, task.getTypeSymbol(), task.getStatusSymbol(),
                    task.getDesc());
        }
        return constructMessage(msg);
    }

    /**
     * Constructs an error message
     *
     * @param msg Error message
     * @return A formatted string containing the constructed error message
     */
    public String constructErrorMessage(String msg) {
        return constructMessage(String.format("ERROR MEOW! %s", msg));
    }
}
