/**
 * Ui handles the interactions with the user.
 */
public class Ui {

    /**
     * Prints the welcome message for users when they run the app.
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo + "\n" + "Hello! I'm Duke\n" + "What can I help you with today! :-)";
    }

    /**
     * Prints the response after successfully adding a task.
     * @param task Task that was added.
     * @param taskListSize Number of tasks in the TaskList.
     */
    public String responseToAddTask(Task task, int taskListSize) {
        return "Done! One new task:\n" + task.toString() + "\nNow you have "
                 + taskListSize + ((taskListSize == 1) ? " task" : " tasks") + " in the list";
    }

    /**
     * Prints the response after successfully deleting a task.
     * @param task Task that was deleted.
     * @param taskListSize Number of tasks in the TaskList.
     */
    public String responseToDelete(Task task, int taskListSize) {
        return "Noted, I've removed this task:\n" + task.toString()
                 + "\nNow you have " + taskListSize + ((taskListSize == 1) ? " task" : " tasks") + " in the list";
    }

    /**
     * Prints the response after user inputs 'bye', before program exits.
     */
    public String responseToBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the initial response to the 'list' command, depending on whether
     * the TaskList is empty.
     * @param taskListSize Number of tasks in the TaskList.
     */
    public String responseToList(int taskListSize) {
        if (taskListSize != 0) {
            return "Here are the tasks in your list:";
        } else {
            return "Your list is currently empty! Let's start adding tasks!";
        }
    }

    /**
     * Prints the response after marking a task as completed.
     * @param task Task that was marked completed.
     */
    public String responseToDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    public static String responseToFind() {
        return "Here are the matching tasks in your list:";
    }

    public static String responseToNoMatches() {
        return "There are no matching tasks! Time to add one!";
    }


}
