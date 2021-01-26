/**
 * User Interface class to handle output to user.
 */
public class Ui {

    /**
     * Displays the welcome message when user enters program.
     */
    public void displayWelcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays the closing message when user exits program.
     */
    public void displayClosingMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays lists of tasks.
     *
     * @param taskList TaskList containing list of tasks.
     */
    public void displayListMessage(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList.getList());
    }

    /**
     * Displays message when user adds a task.
     *
     * @param task Task to be added.
     * @param taskList TaskList containing updated list of tasks.
     */
    public void displayTaskAdded(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.taskStatus());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list");
    }

    /**
     * Displays message when user completes a task.
     *
     * @param task Task that was completed.
     */
    public void displayTaskCompleted(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.taskStatus());
    }

    /**
     * Displays message when user deletes a task.
     *
     * @param task Task that was deleted.
     * @param taskList TaskList containing updated list of tasks.
     */
    public void displayTaskDeleted(Task task, TaskList taskList) {
        System.out.println("Noted. I have removed this task:");
        System.out.println(task.taskStatus());
        System.out.println("Now you have " + taskList.getSize() + " tasks in the list");
    }

    /**
     * Displays message when list of tasks is not found.
     */
    public void displayLoadingError() {
        System.out.println("List of Tasks not found.");
    }
}
