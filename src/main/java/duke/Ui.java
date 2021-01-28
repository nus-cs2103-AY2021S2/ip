package duke;

import duke.models.Task;

public class Ui {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String HORIZONTAL_LINE = "_____________________________________________________________________";

    public Ui() {

    }

    public void printIndentOutput(String output) {
        System.out.println('\t' + output);
    }

    public void printHorizontalLine() {
        printIndentOutput(HORIZONTAL_LINE);
    }

    public void printTaskListStatus(TaskList tasks, Task curTask) {
        printIndentOutput("Got it. I've added this task:");
        printIndentOutput("   " + curTask);
        printIndentOutput("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public void printIntro() {
        System.out.println("Hello from\n" + LOGO);

        printHorizontalLine();
        printIndentOutput("What can I do for you?");
        printHorizontalLine();
    }

    public void printError(String errorMessage) {
        printIndentOutput("OOPSIE!! " + errorMessage);
    }
}
