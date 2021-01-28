package jeff;

import java.util.Scanner;

/**
 * Handles interactions with the user, including reading input and printing to console.
 */
public class Ui {
    private static final String INDENT = "        ";
    private static final String BORDER = INDENT + "__________________________________________________";
    private static final String LOGO = "      _       __  __  \n"
                             + "     | |     / _|/ _| \n"
                             + "     | | ___| |_| |_  \n"
                             + " _   | |/ _ \\  _|  _|\n"
                             + "| |__| |  __/ | | |   \n"
                             + " \\____/ \\___|_| |_| \n";
    private final Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a string to console with specified indentation and borders.
     *
     * @param s String to be printed.
     */
    public void print(String s) {
        String[] sSplit = s.split("\n");
        System.out.println(BORDER);
        for(String line: sSplit) {
            System.out.println(INDENT + line);
        }
        System.out.println(BORDER + "\n");
    }

    /**
     * Prints a string to console, formatted as an error.
     *
     * @param s String to be formatted and printed.
     */
    public void printError(String s) {
        print("OOPS!!! Error: " + s);
    }

    /**
     * Prints welcome message to console.
     */
    public void showWelcome() {
        print("Hello I'm\n" + LOGO + "\nWhat can I do for you?");
    }

    /**
     * Reads in a line of input from user.
     *
     * @return Input string.
     */
    public String readMessage() {
        return sc.nextLine();
    }
}
