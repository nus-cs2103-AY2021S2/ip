package duke.ui;

import java.util.Scanner;

/**
 * Interacts with user by receiving input and printing results from user commands.
 */
public class Ui {
    private static final String[] greet = {
        " ____        _        ",
        "|  _ \\ _   _| | _____ ",
        "| | | | | | | |/ / _ \\",
        "| |_| | |_| |   <  __/",
        "|____/ \\__,_|_|\\_\\___|\n",
        "Greetings! I'm Your Personal Assistant Duke:)",
        "What can I do for you today?"
    };

    private static final String[] exit = {
        "Bye. Nice to meet you and hope to see you again soon!"
    };

    private static final String border =
        "    ____________________________________________________________"
            + "_______________\n";

    private static final String indent = "     ";

    private final Scanner in;

    /**
     * Constructor and inits the scanner for input.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Returns the formatted message to be printed.
     * @param messages an array of strings, main body of the message to be formatted.
     * @return the formatted message to be printed.
     */
    public static String formatMessage(String[] messages) {
        StringBuilder res = new StringBuilder(border);
        for (String message : messages) {
            res.append(indent).append(message).append("\n");
        }
        res.append(border);
        return res.toString();
    }

    /**
     * Returns the formatted message to be printed for GUI.
     * @param messages an array of strings, main body of the message to be formatted.
     * @return the formatted message to be printed.
     */
    public static String formatMessageGui(String[] messages) {
        StringBuilder res = new StringBuilder();
        for (String message : messages) {
            res.append(message).append("\n");
        }
        return res.toString();
    }

    /**
     * Returns the greet message to be printed for GUI.
     * @return the greet message to be printed.
     */
    public static String getGreetGui() {
        return "Greetings! I'm Your Personal Assistant Duke:)\n"
            + "What can I do for you today?";
    }

    /**
     * Returns the exit message to be printed for GUI.
     * @return the exit message to be printed.
     */
    public static String getExitGui() {
        return "Bye. Nice to meet you and hope to see you again soon!\n"
                + "Closing...";
    }

    /**
     * Prints the welcome message when duke starts.
     */
    public static void greet() {
        System.out.println(formatMessage(greet));
    }

    /**
     * Prints the exit message when duke closes.
     */
    public static void bye() {
        System.out.println(formatMessage(exit));
    }

    /**
     * Helper method that prints the message or reply after the user command is executed.
     * @param msg message to be printed
     */
    private static void printReply(String msg) {
        System.out.println(msg);
    }

    /**
     * Takes in user input.
     * @return user input as String
     */
    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Prints the result after the command is executed.
     * @param msg result from Command class .execute method
     */
    public static void printMessage(String[] msg) {
        printReply(formatMessage(msg));
    }
}
