/**
 * Represent a Ui used for interacting with the user.
 */
public class Ui {

    /**
     * Welcomes the user.
     */
    void welcome() {
        System.out.println("Hello! I'm Duke. What can I do for you?\n");
    }

    /**
     * Bids the user farewell.
     */
    void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the saved tasks of the user.
     * @param list Saved tasks
     */
    void list(String list) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(list);
    }

    /**
     * Reminds user that a task is completed.
     * @param task Completed task.
     */
    void done(String task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task + "\n");
    }

    /**
     * Reminds user that a task is deleted.
     * @param task Deleted task.
     * @param tasks Number of saved tasks after deleting.
     */
    void delete(String task, int tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %s tasks in the list.", tasks) + "\n");
    }

    /**
     * Reminds user that a task has been saved.
     * @param task Saved task.
     * @param tasks Number of tasks after saving.
     */
    void addTask(String task, int tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %s tasks in the list.", tasks) + "\n");
    }

    /**
     * Reminds user to provide valid input.
     * @param errormsg Error Message.
     */
    void showError(String errormsg) {
        System.out.println(errormsg + "\n");
    }
}
