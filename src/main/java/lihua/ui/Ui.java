package lihua.ui;

import java.util.Scanner;

import lihua.commands.CommandResult;
import lihua.commons.Messages;

public class Ui {
    /** Scanner object to read user input string */
    private final Scanner sc;

    /**
     * Initializes a new Ui object with a Scanner object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Reads the user's next line of input string.
     *
     * @return The user's input string.
     */
    public String getUserInput() {
        return sc.nextLine();
    }

    /**
     * Prints greeting message to the user when the user starts the application.
     */
    public void printHello() {
        printHorizontalLine();
        System.out.println(Messages.MESSAGE_HELLO);
        printHorizontalLine();
    }

    /**
     * Prints a line divider to the user.
     */
    public void printHorizontalLine() {
        System.out.println(Messages.MESSAGE_LINE);
    }

    /**
     * Prints the feedback after a command is executed to the user.
     *
     * @param result The CommandResult object returned by a command's execution.
     */
    public void showFeedbackToUser(CommandResult result) {
        printHorizontalLine();
        System.out.println(result.getFeedBack());
        printHorizontalLine();
    }

    /**
     * Prints a string feedback to the user.
     *
     * @param feedback The feedback string.
     */
    public void showFeedbackToUser(String feedback) {
        printHorizontalLine();
        System.out.println(feedback);
        printHorizontalLine();
    }
}
