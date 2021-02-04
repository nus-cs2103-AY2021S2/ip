package duke.ui;

public class CliUi extends Ui {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String HORIZONTAL_LINE = "____________________________________"
        + "_________________________________";

    @Override
    public String getReplyString() {
        return null;
    }

    @Override
    public void resetReplyString() {

    }

    /**
     * Prints the output with an indent.
     * @param output output to be printed
     */
    @Override
    public void printIndentOutput(String output) {
        System.out.println('\t' + output);
    }

    /**
     * Prints a straight horizontal line.
     */
    @Override
    public void printHorizontalLine() {
        printIndentOutput(HORIZONTAL_LINE);
    }

    /**
     * Prints out the intro of the application.
     */
    @Override
    public void printIntro() {
        System.out.println("Hello from\n" + LOGO);

        printHorizontalLine();
        printIndentOutput("What can I do for you?");
        printHorizontalLine();
    }
}
