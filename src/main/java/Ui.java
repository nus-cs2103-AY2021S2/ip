/**
 * Represent a Ui used for interacting with the user.
 */
public class Ui {

    /**
     * Welcomes the user.
     */
    String welcome() {
        return "Hello! I'm Duke. What can I do for you?\n";
    }

    /**
     * Bids the user farewell.
     */
    String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays the saved tasks of the user.
     * @param list Saved tasks
     */
    String list(String list) {
        return "Here are the tasks in your list:\n" + list;
    }

    /**
     * Reminds user that a task is completed.
     * @param task Completed task.
     */
    String done(String task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Reminds user that a task is deleted.
     * @param task Deleted task.
     * @param tasks Number of saved tasks after deleting.
     */
    String delete(String task, int tasks) {
        return "Noted. I've removed this task:\n" + task + "\n"
                + String.format("Now you have %s tasks in the list.", tasks);
    }

    /**
     * Reminds user of saved tasks found.
     * @param string Saved tasks found.
     */
    String find(String string) {
        return "Here are the matching tasks in your list:\n" + string;
    }

    /**
     * Reminds user that a task has been saved.
     * @param task Saved task.
     * @param tasks Number of tasks after saving.
     */
    String addTask(String task, int tasks) {
        return "Got it. I've added this task:\n" + task + "\n"
                + String.format("Now you have %s tasks in the list.", tasks);
    }

    String updateTask(String task) {
        return "Noted. I've edited this task:\n" + task;
    }

    /**
     * Reminds user to provide valid input.
     * @param errormsg Error Message.
     */
    String showError(String errormsg) {
        return errormsg;
    }
}
