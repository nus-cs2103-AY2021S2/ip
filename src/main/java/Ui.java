
public class Ui {

    protected String respone;

    /**
     * Respone when the user activated Duke.
     *
     * @return welcome
     */
    public String showWelcome() {
        respone = "-------------------------------"
                + "--------------------------------\n"
                + "Hello! I'm Duke\n" + "What can I do for you?\n"
                + "-------------------------------"
                + "--------------------------------";
        return respone;
    }

    /**
     * Respone when there is a error.
     *
     * @param error
     * @return error
     */
    public String showError(String error) {
        respone = error;
        return respone;
    }

    /**
     * Respone when user said bye.
     *
     * @return goodbye
     */
    public String showGoodBye() {
        respone = "Bye. Hope to see you again soon";
        return respone;
    }

    /**
     * Line to divide the text box.
     *
     * @return line
     */
    public String showLine() {
        respone = "-------------------------------------";
        return respone;
    }

    /**
     * Respone when user want to list out the tasks.
     *
     * @return list
     */
    public String showList() {
        respone = "Here are the tasks in your list:";
        return respone;
    }

    /**
     * Respone when user want to add a task.
     *
     * @param t task
     * @param size number of task
     * @return added
     */
    public String showAddMessage(Task t, int size) {
        respone = "Got it. I've added this task:\n"
                + "  " + t + "\nNow you have "
                + size + " tasks in the list.";
        return respone;
    }

    /**
     * Respone when user finish a task.
     *
     * @param t task
     * @return done
     */
    public String showDone(Task t) {
        respone = "Nice! I've marked this task as done:\n  " + t;
        return respone;
    }

    /**
     * Respone when user want to delete a task.
     *
     * @param t task
     * @param size number of task
     * @return delete
     */
    public String showDelete(Task t, int size) {
        respone = "Noted. I've removed this task:\n  "
                + t + "\nNow you have "
                + size + " tasks in the list.";
        return respone;
    }

    /**
     * Respone when user want to find tasks.
     *
     * @return find
     */
    public String showFind() {
        respone = "Here are the matching tasks in your list:";
        return respone;
    }

    /**
     * Respone when user want to search for tasks on certain date.
     *
     * @param time date
     * @return date
     */
    public String showDate(String time) {
        respone = "Here are the tasks occurring on " + time + " in your list:";
        return respone;
    }
}
