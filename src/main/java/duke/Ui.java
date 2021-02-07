package duke;

import java.util.Scanner;

/**
 * The Ui class handles command-line based input from a user and
 * output to be displayed to the user of a running instance of Duke.
 */
public class Ui {
    private Scanner scanner;
    private static final String DIVIDER_LINE = "____________________________________________________________";
    private static final String LOGO = "                            _       \n" +
            "                           (_)      \n" +
            " _ __ ___   __ _ _ ____   ___ _ __  \n" +
            "| '_ ` _ \\ / _` | '__\\ \\ / / | '_ \\ \n" +
            "| | | | | | (_| | |   \\ V /| | | | |\n" +
            "|_| |_| |_|\\__,_|_|    \\_/ |_|_| |_|";
    private static final String WELCOME_MESSAGE = "Hello! I'm Marvin what can I do for you today?";
    private static final String LOADING_ERROR_MESSAGE = "Sorry was unable to load tasks from storage!";

    /**
     * Constructs an instance of a Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a divider line to be shown to the user.
     */
    public void showLine() {
        System.out.println(DIVIDER_LINE);
    }

    /**
     * Prints a welcome message to be shown to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println(LOGO);
        System.out.println(WELCOME_MESSAGE);
        showLine();
    }

    /**
     * Reads a command from the user.
     */
    public String readCommand() {
        System.out.print(">>> ");
        return scanner.nextLine();
    }

    /**
     * Prints an error message when tasks failed to load from file to be shown to the user.
     */
    public void showLoadingError() {
        System.out.println(LOADING_ERROR_MESSAGE);
    }

    /**
     * Prints the corresponding error message to be shown to the user.
     * @param message The corresponding error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints the corresponding message to be shown to the user without a newline.
     * @param message The corresponding message.
     */
    public void show(String message) {
        System.out.print(message);
    }

    /**
     * Prints the corresponding message to be shown to the user with a newline.
     * @param message The corresponding message.
     */
    public void showNewLine(String message) {
        System.out.println(message);
    }
}
