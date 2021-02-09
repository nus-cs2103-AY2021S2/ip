/**
 * User Interface class to handle output to user.
 */
public class Ui {

    /**
     * Displays the welcome message when user enters program.
     */
    public String displayWelcomeMessage() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Displays the closing message when user exits program.
     */
    public String displayClosingMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays lists of tasks.
     *
     * @param taskList TaskList containing list of tasks.
     */
    public String displayListMessage(TaskList taskList) {
        String output = "Here are the tasks in your list:\n" + taskList.getList();
        return output;
    }

    /**
     * Displays message when user adds a task.
     *
     * @param task Task to be added.
     * @param taskList TaskList containing updated list of tasks.
     */
    public String displayTaskAdded(Task task, TaskList taskList) {
        String output = "Got it. I've added this task:\n";
        output = output + task.taskStatus() + "\n";
        output = output + "Now you have " + taskList.getSize() + " tasks in the list";

        return output;
    }

    /**
     * Displays message when user completes a task.
     *
     * @param task Task that was completed.
     */
    public String displayTaskCompleted(Task task) {
        String output = "Nice! I've marked this task as done:\n" + task.taskStatus();
        return output;
    }

    /**
     * Displays message when user deletes a task.
     *
     * @param task Task that was deleted.
     * @param taskList TaskList containing updated list of tasks.
     */
    public String displayTaskDeleted(Task task, TaskList taskList) {
        String output = "Noted. I have removed this task:\n";
        output = output + task.taskStatus() + "\n";
        output = output + "Now you have " + taskList.getSize() + " tasks in the list";

        return output;
    }

    /**
     * Displays list of tasks containing a given keyword.
     *
     * @param keyword Keyword used to search through tasks.
     * @param taskList TaskList containing list of tasks to be searched through.
     */
    public String displayTaskSearch(String keyword, TaskList taskList) {
        String output = "Here are the matching tasks in your list:\n";
        output = output + taskList.search(keyword);
        return output;
    }

}
