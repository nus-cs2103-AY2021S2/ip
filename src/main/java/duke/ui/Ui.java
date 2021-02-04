package duke.ui;

import duke.TaskList;
import duke.models.Task;

/**
 * Manages the Command line interface of the application.
 */
public abstract class Ui {
    public abstract void resetReplyString();
    public abstract String getReplyString();

    /**
     * Prints the output with an indent.
     * @param output output to be printed
     */
    public abstract void printIndentOutput(String output);

    /**
     * Prints a straight horizontal line.
     */
    public abstract void printHorizontalLine();

    /**
     * Print the current status of tasks.
     * @param tasks list of task
     * @param curTask relevant task that was added / deleted
     */
    public void printTaskListStatus(TaskList tasks, Task curTask) {
        printIndentOutput("Got it. I've added this task:");
        printIndentOutput("   " + curTask);
        printIndentOutput("Now you have " + tasks.size() + " task(s) in the list.");
    }

    /**
     * Prints out the intro of the application.
     */
    public abstract void printIntro();

    /**
     * Prints out the given error message in the application.
     * @param errorMessage error message
     */
    public void printError(String errorMessage) {
        printIndentOutput("OOPSIE!! " + errorMessage);
    }
}
