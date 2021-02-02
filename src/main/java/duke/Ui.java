package duke;

/**
 * Encapsulates the Ui component of Duke.
 * 
 * @author Aaron Saw Min Sern
 */
public class Ui {
    private static final String LOGO = " ____        _        \n" + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE = "────────────────────────────────────────────────────────────────────────────────";

    /**
     * Prints the starting header logo and text to the command line.
     */
    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Prints a horizontal line to the command line.
     */
    public void showLine() {
        System.out.println(LINE);
    }
}
