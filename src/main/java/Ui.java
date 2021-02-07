/**
 * A class that control how Duke interacts with user.
 */
public class Ui {

    /**
     * Return a message with an additional newline
     * @param message Message wanted to be shown
     */
    public static String showMessage(String message) {
        return message + "\n";
    }

    /**
     * Returns a welcome message when starting Duke
     */

    public static String getWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        return "Hello from\n" + logo + "\nHello! I'm Fayola, Duke's Assistant.\n" +
                "What can I do for you?\n";
    }

    /**
     * Returns a closing message when closing Duke
     */
    public static String doBye() {
        return "Bye. Hope to see you again soon!\n";
    }


    /**
     * Returns a message when finishing a Task
     * @param message message of finishing a task
     */
    public static String finishTaskMessage(String message) {
        return "Nice! I've marked this task as done: \n" + " " + message + "\n";
    }

    /**
     * Returns a message when deleting a Task
     * @param message message of deleting a task
     * @param newSize new size of the TaskList
     */
    public static String deleteTaskMessage(String message, int newSize) {
        return "Noted. I've removed this task:\n"  + "  " + message + "\nNow you have " + newSize
                + " tasks in the list.\n";
    }

    /**
     * Returns a message when adding a Task
     * @param message messsage of adding a task
     * @param newSize new size of the TaskList
     */
    public static String addTaskMessage(String message, int newSize) {
        return "Got it. I've added this task:\n" + "  " + message + "\nNow you have "
                + newSize + " tasks in the list.";
    }
}
