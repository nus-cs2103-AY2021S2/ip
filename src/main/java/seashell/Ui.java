package seashell;

public class Ui {
    public static final String HELP_TEXT = "Welcome to Seashells! You can start by trying out some of these commands"
            + "\n todo <task name> - add a todo task"
            + "\n deadline <task name> /by <datetime> - add a deadline task"
            + "\n event <task name> /at <datetime> - add an event task"
            + "\n done <task index> - mark the task at the specified index as done"
            + "\n delete <task index> - remove the task at the specified index from the list"
            + "\n clear - clears the task list"
            + "\n bye - exits the program";

    protected void showWelcome() {
        System.out.println("Hello I'm Seashell, a task manager! What can I do for you? Type \"help\" for more "
                + "information on the commands you can give me!");
    }

    protected void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    protected void showError(String errMessage) {
        System.err.println(errMessage);
    }
}
