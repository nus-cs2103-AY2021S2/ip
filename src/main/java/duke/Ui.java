package duke;

import java.util.ArrayList;

/**
 * Provides the responses to the user for each command case.
 */
public class Ui {

    /**
     * Initialises the Ui object.
     */
    public Ui() {
    }

    /**
     * Returns string of the welcome message to be shown when the user starts up Duke.
     */
    public String showWelcomeMessage() {
        return "Hey, I'm Duke!\n" + "How can I help you?";
    }

    /**
     * Returns string of the goodbye message to be shown when the user types 'bye' command.
     */
    public String showGoodbyeMessage() {
        return "Aw. It was nice chatting with you! Don't forget me! :)";
    }

    /**
     * Returns string of a response to an unrecognised command.
     */
    public String showInvalidTaskMessage() {
        return "Oops! I don't know what this means! :(";
    }

    /**
     * Returns string of the task the user just added and the current size of the task list.
     *
     * @param task the task the user added.
     */
    public String showTaskAdded(Task task) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\nNow you have "
                + TaskList.taskListSize + " task(s) in the list.";
    }

    /**
     * Returns string of the task the user just deleted and the current size of the task list.
     *
     * @param task the task the user deleted.
     */
    public String showTaskDeleted(Task task) {
        return "Gotcha. I've removed this task:\n"
                + task.toString() + "\nNow you have "
                + TaskList.taskListSize + " task(s) in the list.";
    }

    /**
     * Returns string of the task marked as done.
     *
     * @param task the task the user marked as done.
     */
    public String showTaskDone(Task task) {
        return "Nice job! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Returns string of the current list of tasks. If there are no tasks, Ui will provide a different prompt.
     *
     * @param taskList the current list of tasks.
     */
    public String showTaskList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            return "Looks like you have no tasks currently. Add some tasks!";
        } else {
            String message = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task item = taskList.get(i);
                message += (i + 1) + "." + item.toString() + "\n";
            }
            return message;
        }
    }

    /**
     * Returns string of matching tasks. If there are no tasks, Ui will provide a different prompt.
     *
     * @param matchingTasks the list of tasks that has matching keyword.
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.size() == 0) {
            return "Looks like there were no tasks matching your search, try again?";
        } else {
            String message = "Here are the matching tasks in your list:";
            for (int i = 0; i < matchingTasks.size(); i++) {
                Task item = matchingTasks.get(i);
                message += (i + 1) + "." + item.toString() + "\n";
            }
            return message;
        }
    }
}
