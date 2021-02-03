/**
 * UI class is a class that handles all aspects of IO in Duke.
 */
public class Ui {

    /**
     * Show Duke's welcome text
     */
    public String showWelcome() {
        return "Hello, I am Duke, your personal Assistant. How may I help you today?";
    }

    /**
     * Show number of items in the task list.
     * @param numOfItems
     */
    public String showNumberOfItems(int numOfItems) {
        return "Now you have " + numOfItems + " tasks in the list.";
    }

    /**
     * Shows the task that was added to the list.
     * @param task
     */
    public String showTaskAdded(Task task) {
        return "Got it. I've added this task: \n" + task.toString();
    }

    /**
     * Show the task being marked as done.
     * @param task
     */
    public String showTaskDone(Task task) {
        return "Nice! I've marked this task as done: \n" + task.toString();
    }

    /**
     * Show the list of tasks.
     */
    public String showListItems(TaskList taskList) {
        StringBuilder sb = new StringBuilder("Here are the tasks on your list: \n");
        for (int i = 1; i <= taskList.getTaskListLength(); i++) {
            sb.append(String.format("%s. %s", i, taskList.getTaskAtIndex(i - 1).toString()) + "\n");
        }
        return sb.toString();
    }

    /**
     * Shows the task deleted.
     * @param task task to be deleted
     */
    public String showTaskDeleted(Task task) {
        return "Noted. I've removed this task: \n" + task.toString();
    }

    /**
     * Show good bye to user.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows the error message from exception.
     * @param e exception encountered.
     */
    public String showLoadingError(Exception e) {
        return e.getMessage();
    }

    public String showFoundListItems(TaskList taskList, String command) {
        String[] commandArr = command.split(" ");
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list: \n");
        for (int i = 1; i <= taskList.getTaskListLength(); i++) {
            if (taskList.getTaskAtIndex(i - 1).getDescription().contains(commandArr[1])) {
                sb.append(String.format("%s. %s", i, taskList.getTaskAtIndex(i - 1).toString()) + "\n");
            }
        }
        return sb.toString();
    }
}
