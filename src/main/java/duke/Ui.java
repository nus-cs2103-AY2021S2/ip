package duke;

/**
 * This class acts as a placeholder for responses to be responded to the user
 */
public class Ui {

    private String response;

    public Ui() {
        response = "";
    }

    /**
     * modifies response to an empty string
     *
     */
    public void clearResponse() {
        response = "";
    }

    /**
     * Returns String of response
     *
     * @return response
     */
    public String getResponse() {
        return this.response;
    }

    /**
     * append the string specified to the response
     *
     * @param msg message to be appended
     */

    public void appendResponse(String msg) {
        response += msg;
    }

    /**
     * append a general error message to the reponse
     *
     */
    public void showLoadingError() {
        response += "Error occurred while loading\n";
    }

    /**
     * append line to the reponse
     *
     */
    public void showLine() {
        response += "_____________________________________________\n";
    }

    /**
     * append specified error message to the reponse
     *
     * @param msg error message
     */
    public void showError(String msg) {
        response += (msg + "\n");
    }

    /**
     * appends textual form of task to the response
     *
     * @param task task
     */
    public void printTask(Task task) {
        response += task.toString() + "\n";
        if (task.toString().isEmpty()) {
            System.out.println("Nothing");
        }
        System.out.println("Print task: " + task.toString());
    }

    /**
     * appends textual form of task from a tasklist to the response
     *
     * @param taskList taskList
     */
    public void printTasks(TaskList taskList) {
        int i = 1;
        for (Task t : taskList.getTaskList()) {
            response += (i + ". " + t.toString() + "\n");
            i++;
        }
    }

    /**
     * appends list header message to the response
     *
     */
    public void showListMsg() {
        response += "Here are the tasks in your list:\n";
    }

    /**
     * appends list is empty message to the response
     *
     */
    public void showEmptyListMsg() {
        response += "You do not have any tasks at the moment.\n";
    }
    /**
     * appends goodbye message to the response
     *
     */
    public void showByeMsg() {
        response += "Goodbye comrade. Hope to see you again soon!\n";
    }

    /**
     * appends marking task as done message to the response
     *
     */
    public void showDoneMsg() {
        response += "Nice! I've marked this task as done:\n";
    }

    /**
     * appends task is already done message to the response
     *
     */
    public void showIsAlrDoneMsg() {
        response += "You have already completed this task!\n";
    }

    /**
     * appends added task message to the response
     *
     */
    public void showTaskMsg() {
        response += "Got it. I've added this task:\n";
    }

    /**
     * appends task count message to the response
     *
     */
    public void showTaskCount(int i) {
        response += ("Now you have " + i + " tasks in the list.\n");
    }

    /**
     * appends delete header message to the response
     *
     */
    public void showDeleteMsg() {
        response += "Noted. I've removed this task:\n";
    }

    /**
     * appends cannot find task message to the response
     *
     */
    public void showCannotFind() {
        response += "I'm sorry, I can't find the task requested\n";
    }

    /**
     * appends founded task header message to the response
     *
     */
    public void showFoundText() {
        response += "Here are the matching tasks in your list:\n";
    }

    /**
     * appends task updated message to the response
     *
     */
    public void showTaskUpdated() {
        response += "The task is updated!\n";
    }
}
