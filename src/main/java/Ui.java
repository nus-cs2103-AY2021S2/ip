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
     * Prints the welcome message to be shown when the user starts up Duke.
     */
    public void showWelcomeMessage() {
        System.out.println("Hey, I'm Duke!\n" + "How can I help you?");
    }

    /**
     * Prints the goodbye message to be shown when the user types 'bye' command.
     */
    public void showGoodbyeMessage() {
        System.out.println("Aw. It was nice chatting with you! Don't forget me! :)");
    }

    /**
     * Prints a response to an unrecognised command.
     */
    public void showInvalidTaskMessage() {
        System.out.println("Oops! I don't know what this means! :(");
    }

    /**
     * Prints the task the user just added and the current size of the task list.
     * @param task the task the user added.
     */
    public void showTaskAdded(Task task) {
        System.out.println("Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + TaskList.taskListSize + " task(s) in the list.");
    }

    /**
     * Prints the task the user just deleted and the current size of the task list.
     * @param task the task the user deleted.
     */
    public void showTaskDeleted(Task task) {
        System.out.println("Gotcha. I've removed this task:\n"
                + task.toString()
                + "\nNow you have " + TaskList.taskListSize + " task(s) in the list.");
    }

    /**
     * Prints the task marked as done.
     * @param task the task the user marked as done.
     */
    public void showTaskDone(Task task) {
        System.out.println("Nice job! I've marked this task as done:\n"
                + task.toString());
    }

    /**
     * Prints the current list of tasks. If there are no tasks, Ui will provide a different prompt.
     * @param taskList the current list of tasks.
     */
    public void showTaskList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("Looks like you have no tasks currently. Add some tasks!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task item = taskList.get(i);
                System.out.println((i + 1) + "." + item.toString());
            }
        }
    }
}
