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
    public static final String WELCOME_MESSAGE = "Welcome to DBot";
    private final String NAME = "DougBot";
    private static final String DIVIDER = "===================================================";
    private final Scanner in;

    /**
     * Initializes the default Ui which uses the standard input, output, and error streams.
     * These are the streams that the DBot will use to interact with a user.
     */
    public Ui() {
        this(System.in);
    }

    /**
     * Initializes a Ui according to the specified Input Stream, Print Stream, and Error Stream provided.
     * These are the streams that the DBot will use to interact with a user.
     *
     * @param in The InputStream that DBot should receive user inputs from.
     */
    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    /**
     * Shows the user an error specifying that stored tasks could not be loaded into DBot.
     *
     * @return A String containing a loading error message.
     */
    public String showLoadingError() {
        return "ERROR: Could not load from storage.";
    }

    /**
     * Shows the user a welcome message.
     *
     * @return A String containing a welcome message.
     */
    public String showWelcome() {
        return "Welcome to " + NAME + ".";
    }

    /**
     * Shows the user the Task that was added.
     *
     * @param task The Task that was added.
     * @return A String message that the task was successfully added.
     */
    public String printAddTask(Task task) {
        return "Added: " + task.toString();
    }

    /**
     * Shows the user a list of all existing Tasks.
     *
     * @param tasks An iterable List storing all the users Tasks.
     * @return A String representation of all the tasks in the TaskList.
     */
    public String printTasks(TaskList tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(task.toString());
        }
        return stringBuilder.toString();
    }

    /**
     * Shows the user the Task that was marked as done.
     *
     * @param doneTask The Task that was marked as done.
     * @return A String message that the task was marked as done.
     */
    public String printDone(Task doneTask) {
        return "Completed: " + doneTask.toString();
    }

    /**
     * Shows the user the Task that was deleted.
     *
     * @param deleteTask The Task that was deleted.
     * @return A String message that the task was deleted.
     */
    public String printDelete(Task deleteTask) {
        return "Deleted: " + deleteTask.toString();
    }

    /**
     * Shows the user an exit message.
     *
     * @return A String containing a goodbye message.
     */
    public String showExitMessage() {
        return "Goodbye.";
    }

    /**
     * Shows the user the Help message.
     *
     * @return A String containing the help help message, informing the user about the available commands.
     */
    public String showHelpMessage() {
        String helpMessage = "Use any of the following commands:\n" +
                "To view all stored tasks: list\n" +
                "To find relevant stored tasks: find TERM\n" +
                "To add a new 'todo': todo DESC\n" +
                "To add a new 'event': event DESC /at DATE\n" +
                "To add a new 'deadline': deadline DESC /by DATE\n" +
                "To terminate DBot and save existing tasks: bye\n\n" +
                "\tDESC - Description of the task\n" +
                "\tDATE - Date of the task in YYYY-MM-DD format\n" +
                "\tTERM - A search term that should match a part of a Tasks description";
        return helpMessage;
    }

    /**
     * Shows the user a list of relevant Tasks.
     *
     * This method is to be called when a user attempts to find a Task via the Find Command.
     *
     * @param relevantTasks A TaskList containing relevant Tasks.
     * @return A String containing the representation of a TaskList containing the relevant tasks.
     */
    public String printRelevantTasks(TaskList relevantTasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the matching tasks in your list:");
        stringBuilder.append(printTasks(relevantTasks));
        return stringBuilder.toString();
    }
}
