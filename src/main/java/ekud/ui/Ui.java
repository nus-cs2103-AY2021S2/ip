package ekud.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * The user interface class that handles all the interactions with the user
 * All input and output from the user will go through this class for better abstraction
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String PADDING = "\t";
    private static final String WELCOME_MESSAGE = "Yo! I'm Ekud!\nWhat you want?";
    private final Scanner input;
    private final PrintStream output;

    /**
     * Construct a new Ui that will use the given InputStream and PrintStream as the input and output.
     *
     * @param input  The input stream.
     * @param output The output stream.
     */
    public Ui(InputStream input, PrintStream output) {
        this.input = new Scanner(input);
        this.output = output;
    }

    /**
     * Construct a new Ui using the system's default input and output.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Show a horizontal line used as a separator.
     */
    public void showLine() {
        output.println(PADDING + HORIZONTAL_LINE);
    }

    /**
     * Display any number of lines to the user.
     *
     * @param lines Any String iterable that will all be displayed, each separated by line breaks.
     */
    public void printLines(String... lines) {
        for (String line : lines) {
            output.println(PADDING + " " + line);
        }
    }

    /**
     * Display the welcome message of Ekud.
     */
    public void showWelcome() {
        showLine();
        printLines(WELCOME_MESSAGE.split("\n"));
        showLine();
    }

    /**
     * Read a single line of input from user.
     *
     * @return The line of input entered by user.
     */
    public String readCommand() {
        String fullCommand = input.nextLine();
        while (fullCommand.isBlank()) {
            fullCommand = input.next();
        }
        return fullCommand;
    }

    /**
     * Display any error message from exception.
     *
     * @param errorMessage The String of the error message.
     */
    public void showError(String errorMessage) {
        printLines(errorMessage);
    }

    /**
     * Display the loading error caused by errors encountered while loading save file.
     */
    public void showLoadingError() {
        printLines("Error while loading saved tasks",
                "Starting from scratch instead...");
    }
}
