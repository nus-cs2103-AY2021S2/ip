package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * A class to handle user interface.
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs Ui.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs Ui with InputStream and PrintStream.
     * @param in The input stream of the user interface.
     * @param out The print stream of the user interface.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Displays the Duke logo and greets the user.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.out.println("Hello from\n" + logo);

        String greet = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        this.out.println(greet);
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        this.out.println("    ____________________________________________________________");
    }

    /**
     * Reads inputs from user.
     * @return The user input.
     */
    public String readCommand() {
        return this.in.nextLine();
    }

    /**
     * Prints error message when there is a load error.
     */
    public void showLoadingError() {
        this.out.println("     No previous data found.");
    }

    /**
     * Prints given error message.
     * @param message The error message to be printed.
     */
    public void showError(String message) {
        this.out.println(message);
    }
}
