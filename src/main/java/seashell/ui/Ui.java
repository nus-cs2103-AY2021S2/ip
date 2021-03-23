package seashell.ui;

public class Ui {
    public static final String HELP_TEXT = "Welcome to Seashells! "
            + "Here is a list of commands you can do and its descriptions:"
            + "\n todo <task name> - add a todo task"
            + "\n deadline <task name> /by <datetime> - add a deadline task"
            + "\n event <task name> /at <datetime> - add an event task"
            + "\n list - list out the tasks that are currently in the task list"
            + "\n done <task index> - mark the task at the specified index as done"
            + "\n delete <task index> - remove the task at the specified index from the list"
            + "\n clear - clears the task list"
            + "\n bye - exits the program";

    /**
     * Show the welcome message when user starts the application
     * @return welcome message in a string
     */
    public static String showWelcome() {
        return "Hello I'm Seashell, a task manager! What can I do for you? Type \"help\" for more "
                + "information on the commands you can give me!";
    }

    public static String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    public static void showError(String errMessage) {
        System.err.println(errMessage);
    }
}
