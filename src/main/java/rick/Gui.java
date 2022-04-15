package rick;

/**
 * Handles interactions with the user on a graphical interface
 * using a <code>Gui</code> object.
 */
public class Gui {
    /**
     * This method is used to output a loading file error message.
     */
    public static String getLoadingErrorString() {
        return "Rick failed to load file. Rick failure. Exiting...";
    }

    /**
     * This method is used to output a saving file error message.
     */
    public static String getSavingErrorString() {
        return "Rick failed to save file. Rick failure. Exiting...";
    }

    /**
     * This method is used to output the welcome message.
     */
    public static String getWelcomeString() {
        return "Hello Morty! It's me, Rick!\n"
                + "What can I do for you?\n"
                + "Type \"help\" to view the list of available commands.";
    }

    /**
     * This method is used to output the goodbye message.
     */
    public static String getGoodbyeString() {
        return "Goodbye Morty. It's been fun while it lasted!";
    }

    /**
     * This method is used to output the list of commands
     * available on the application.
     */
    public static String getHelpMessageString() {
        return "Here you go Morty! This is the list of available commands:\n"
                + "bye -> To exit application\n"
                + "list -> To list tasks\n"
                + "done <index> -> To mark task as done \n"
                + "delete <index> -> To delete task\n"
                + "todo <description> -> To create todo\n"
                + "deadline <description> /by <yyyy-mm-dd> -> To create deadline\n"
                + "event <description> /on <yyyy-mm-dd> -> To create event";
    }

    /**
     * This method is used to output the list of tasks
     * in the <code>TaskList</code> object.
     *
     * @param tasks The <code>TaskList</code> object.
     * @see TaskList
     */
    public static String getTasksString(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        if(tasks.isEmpty()) {
            message.append("List is empty Morty!");
        } else {
            message.append("Okay Morty. Here are your list of tasks:\n");
            for(int i = 1; i <= tasks.getSize(); i++) {
                message.append("  " + i + ". " + tasks.getTask(i - 1));
                if(i < tasks.getSize()) {
                    message.append("\n");
                }
            }
        }
        return message.toString();
    }

    /**
     * This method is used to output the list of found tasks
     * in the <code>TaskList</code> object.
     *
     * @param tasks The <code>TaskList</code> object.
     * @see TaskList
     */
    public static String getFoundTasksString(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        if(tasks.isEmpty()) {
            message.append("Sorry Morty. Task not found. Rick failure.");
        } else {
            message.append("Okay Morty. Here are the matching tasks in your list:\n");
            for(int i = 1; i <= tasks.getSize(); i++) {
                message.append("  " + i + ". " + tasks.getTask(i - 1));
                if(i < tasks.getSize()) {
                    message.append("\n");
                }
            }
        }
        return message.toString();
    }

    /**
     * This method is used to output the message provided in a standardized
     * styling with a divider before and after the message.
     *
     * @param message The message to display within the dividers.
     */
    public static String getMessageString(String message) {
        return message;
    }

    /**
     * This method is used to output the generic error message.
     *
     * @param errorMessage The error message to display.
     */
    public static String getErrorMessageString(String errorMessage) {
        return "Error, Morty! Error! " + errorMessage;
    }
}