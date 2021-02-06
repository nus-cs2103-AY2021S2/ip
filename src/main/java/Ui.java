
public class Ui {

    protected String response;

    public String getMessage() {
        return response;
    }

    /**
     * Response when the user activated Duke.
     */
    public void showWelcome() {
        response = "Hello! I'm Duke\n" + "What can I do for you?";
    }

    /**
     * Response when there is a error.
     *
     * @param error error
     */
    public void showError(String error) {
        response = error;
    }

    /**
     * Response when user said bye.
     */
    public void showGoodBye() {
        response = "Bye. Hope to see you again soon";
    }

    /**
     * Response when user want to list out the tasks.
     */
    public void showList(String list) {
        response = "Here are the tasks in your list:\n" + list;
    }

    /**
     * Response when user want to add a task.
     *
     * @param t task.
     * @param size number of task.
     */
    public void showAddMessage(Task t, int size) {
        response = "Got it. I've added this task:\n"
                + "  " + t + "\nNow you have "
                + size + " tasks in the list.";
    }

    /**
     * Response when user finish a task.
     *
     * @param t task.
     */
    public void showDone(Task t) {
        response = "Nice! I've marked this task as done:\n  " + t;
    }

    /**
     * Response when user want to delete a task.
     *
     * @param t task.
     * @param size number of task.
     */
    public void showDelete(Task t, int size) {
        response = "Noted. I've removed this task:\n  "
                + t + "\nNow you have "
                + size + " tasks in the list.";
    }

    /**
     * Response when user want to find tasks.
     */
    public void showFind(String list) {
        response = "Here are the matching tasks in your list:\n" + list;
    }

    /**
     * Response when user want to search for tasks on certain date.
     *
     * @param time date.
     */
    public void showDate(String time, String list) {
        response = "Here are the tasks occurring on " + time + " in your list:\n" + list;
    }
}
