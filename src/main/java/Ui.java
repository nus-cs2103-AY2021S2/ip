
public class Ui {

    protected String response;

    /**
     * Response when the user activated Duke.
     *
     * @return welcome.
     */
    public String showWelcome() {
        response = "-------------------------------"
                + "--------------------------------\n"
                + "Hello! I'm Duke\n" + "What can I do for you?\n"
                + "-------------------------------"
                + "--------------------------------";
        return response;
    }

    /**
     * Response when there is a error.
     *
     * @param error error
     * @return message
     */
    public String showError(String error) {
        response = error;
        return response;
    }

    /**
     * Response when user said bye.
     *
     * @return goodbye.
     */
    public String showGoodBye() {
        response = "Bye. Hope to see you again soon";
        return response;
    }

    /**
     * Line to divide the text box.
     *
     * @return line.
     */
    public String showLine() {
        response = "-------------------------------------";
        return response;
    }

    /**
     * Response when user want to list out the tasks.
     *
     * @return list.
     */
    public String showList() {
        response = "Here are the tasks in your list:";
        return response;
    }

    /**
     * Response when user want to add a task.
     *
     * @param t task.
     * @param size number of task.
     * @return added.
     */
    public String showAddMessage(Task t, int size) {
        response = "Got it. I've added this task:\n"
                + "  " + t + "\nNow you have "
                + size + " tasks in the list.";
        return response;
    }

    /**
     * Response when user finish a task.
     *
     * @param t task.
     * @return done.
     */
    public String showDone(Task t) {
        response = "Nice! I've marked this task as done:\n  " + t;
        return response;
    }

    /**
     * Response when user want to delete a task.
     *
     * @param t task.
     * @param size number of task.
     * @return delete.
     */
    public String showDelete(Task t, int size) {
        response = "Noted. I've removed this task:\n  "
                + t + "\nNow you have "
                + size + " tasks in the list.";
        return response;
    }

    /**
     * Response when user want to find tasks.
     *
     * @return find.
     */
    public String showFind() {
        response = "Here are the matching tasks in your list:";
        return response;
    }

    /**
     * Response when user want to search for tasks on certain date.
     *
     * @param time date.
     * @return date.
     */
    public String showDate(String time) {
        response = "Here are the tasks occurring on " + time + " in your list:";
        return response;
    }
}
