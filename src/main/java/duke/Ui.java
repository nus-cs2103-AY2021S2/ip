package duke;

import duke.models.Task;

/**
 * Manages the Command line interface of the application.
 */
public class Ui {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String HORIZONTAL_LINE = "____________________________________"
        + "_________________________________";

    public Ui() {

    }

    /**
     * Prints the output with an indent.
     * @param output output to be printed
     */
    public void printIndentOutput(String output) {
        System.out.println('\t' + output);
    }

    /**
     * Prints a straight horizontal line.
     */
    public void printHorizontalLine() {
        printIndentOutput(HORIZONTAL_LINE);
    }

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
    public void printIntro() {
        System.out.println("Hello from\n" + LOGO);

        printHorizontalLine();
        printIndentOutput("What can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints out the given error message in the application.
     * @param errorMessage error message
     */
    public void printError(String errorMessage) {
        printIndentOutput("OOPSIE!! " + errorMessage);
    }
}
