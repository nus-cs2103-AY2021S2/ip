package checklst.ui;

import java.util.Scanner;

/**
 * The Ui class handles direct I/O from the user.
 */
public class Ui {

    private static final String WELCOME_MESSAGE = "Hello I'm Checklst! What can I do for you?";

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Prints output in a standard format.
     */
    public void sendOutput(String output) {
        System.out.println("\t----------------------------------------");
        System.out.println("\t" + output);
        System.out.println("\t----------------------------------------");
    }

    /**
     * Prints Welcome output.
     */
    public void sendWelcome() {
        sendOutput(WELCOME_MESSAGE);
    }

    /**
     * Takes in next command and splits it by first whitespace.
     * @return String array of size 2 split by first whitespace.
     */
    public String[] readCommand() {
        return scanner.nextLine().split(" ", 2);
    }

}
