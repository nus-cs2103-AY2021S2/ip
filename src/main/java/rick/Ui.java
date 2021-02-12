package rick;

/**
 * Handles interactions with the user using a <code>Ui</code> object.
 */
public class Ui {
    public static final String logo = " ____        _\n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String divider = "____________________________________________________________\n";

    /**
     * This method is used to output an input error message.
     */
    public void showInputError() {
        showMessage("I'm sorry, but I don't know what that means.");
    }

    /**
     * This method is used to output a loading file error message.
     */
    public void showLoadingError() {
        showMessage("Failed to load file. Exiting...");
    }

    /**
     * This method is used to output a saving file error message.
     */
    public void showSavingError() {
        showMessage("Failed to save file. Exiting...");
    }

    /**
     * This method is used to output the welcome message.
     */
    public void showWelcome() {
        showMessage(logo + "\nHello! I'm Duke!\n"
                + "What can I do for you?\n"
                + "Type \"help\" to view the list of available commands.");
    }

    /**
     * This method is used to output the goodbye message.
     */
    public void showGoodbye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * This method is used to output the list of commands available
     * on the application.
     */
    public void showHelpMessage() {
        showMessage("List of Available Commands:\n"
                + "bye -> To exit application\n"
                + "list -> To list tasks\n"
                + "done [index] -> To mark task as done \n"
                + "delete [index] -> To delete task\n"
                + "todo [description] -> To create todo\n"
                + "deadline [description] /by [date] -> To create deadline\n"
                + "event [description] /on [date] -> To create event");
    }

    /**
     * This method is used to output the list of tasks in
     * the <code>TaskList</code> object.
     *
     * @param tasks The <code>TaskList</code> object.
     * @see TaskList
     */
    public void showTasks(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        if(tasks.isEmpty()) {
            message.append("List is empty.");
        } else {
            message.append("Here are your list of tasks:\n");
            for(int i = 1; i <= tasks.getSize(); i++) {
                message.append("  " + i + ". " + tasks.getTask(i - 1));
                if(i < tasks.getSize()) {
                    message.append("\n");
                }
            }
        }
        showMessage(message.toString());
    }

    /**
     * This method is used to output the list of found tasks
     * in the <code>TaskList</code> object.
     *
     * @param tasks The <code>TaskList</code> object.
     * @see TaskList
     */
    public void showFoundTasks(TaskList tasks) {
        StringBuilder message = new StringBuilder();
        if(tasks.isEmpty()) {
            message.append("Sorry! Task not found.");
        } else {
            message.append("Here are the matching tasks in your list:\n");
            for(int i = 1; i <= tasks.getSize(); i++) {
                message.append("  " + i + ". " + tasks.getTask(i - 1));
                if(i < tasks.getSize()) {
                    message.append("\n");
                }
            }
        }
        showMessage(message.toString());
    }

    /**
     * This method is used to output the message provided in a standardized
     * styling with a divider before and after the message.
     *
     * @param message The message to display within the dividers.
     */
    public void showMessage(String message) {
        System.out.println(divider + message + "\n" + divider);
    }

    /**
     * This method is used to output the generic error message.
     * 
     * @param errorMessage The error message to display.
     */
    public void showErrorMessage(String errorMessage) {
        showMessage("Error! " + errorMessage);
    }
}
