package dbot.ui;

import dbot.task.Task;
import dbot.tasklist.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


/**
 * A User-Interface class that handles interaction with the User including
 * getting user input and printing responses.
 */
public class Ui {
    private final String NAME = "DougBot";
    private static final String DIVIDER = "===================================================";
    private final Scanner in;
    private final PrintStream out;
    private final PrintStream err;

    /**
     * Default initializer which uses the standard input, output, and error streams.
     */
    public Ui() {
        this(System.in, System.out, System.err);
    }

    /**
     * Initializer of the UI that allows users to specify the Input Stream, Print Stream, and Error
     * Streams, that DBot should use, respectively.
     *
     * @param in The InputStream that DBot should receive user inputs from.
     * @param out The PrintStream that DBot should print standard messages to.
     * @param err The PrintStream that DBot should print error messages to.
     */
    public Ui(InputStream in, PrintStream out, PrintStream err) {
        this.in = new Scanner(in);
        this.out = out;
        this.err = err;
    }

    /**
     * Shows the user an error specifying that stored tasks could not be loaded into DBot.
     */
    public void showLoadingError() {
        out.println("ERROR: Could not load from storage.");
    }

    /**
     * Shows the user a welcome message.
     */
    public void showWelcome() {
        out.println("Welcome to " + NAME + ".");
    }

    /**
     * Requests for the user's input.
     *
     * @return A String containing the users input.
     */
    public String getUserInput() {
        out.print("Enter command: ");
        String fullUserInput = in.nextLine();
        return fullUserInput.strip();
    }

    /**
     * Shows the user a line that acts as a divider between IO interactions.
     */
    public void showLine() {
        out.println(DIVIDER);
    }

    /**
     * Shows the user the Task that was added.
     *
     * @param task The Task that was added.
     */
    public void printAddTask(Task task) {
        out.println("Added: " + task.toString());
    }

    /**
     * Shows the user a list of all existing Tasks.
     *
     * @param tasks An iterable List storing all the users Tasks.
     */
    public void printTasks(TaskList tasks) {
        for (Task task : tasks) {
            out.println(task.toString());
        }
    }

    /**
     * Shows the user the Task that was marked as done.
     *
     * @param doneTask The Task that was marked as done.
     */
    public void printDone(Task doneTask) {
        out.println("Completed: " + doneTask.toString());
    }

    /**
     * Shows the user the Task that was deleted.
     *
     * @param deleteTask The Task that was deleted.
     */
    public void printDelete(Task deleteTask) {
        out.println("Deleted: " + deleteTask.toString());
    }

    /**
     * Shows the user an exit message.
     */
    public void showExitMessage() {
        out.println("Goodbye.");
    }

    /**
     * Shows the user the Help message.
     */
    public void showHelpMessage() {
        String helpMessage = "Use any of the following commands:\n" +
                "To view all stored tasks: 'list'\n" +
                "To add a new 'todo': todo DESC\n" +
                "To add a new 'event': event DESC /at DATE\n" +
                "To add a new 'deadline': deadline DESC /by DATE\n" +
                "To terminate DBot and save existing tasks: 'bye'\n\n" +
                "\tDESC - Description of the task\n" +
                "\tDATE - Date of the task in YYYY-MM-DD format";
        out.println(helpMessage);
    }

    /**
     * Shows the user an error message via the appropriate PrintStream.
     *
     * @param message The error message to show to the user.
     */
    public void showError(String message) {
        err.println(message);
    }
}
