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
     * @param output String to be sent.
     * @return Formatted String.
     */
    public String sendOutput(String output) {
        String line = "\t----------------------------------------";
        return line + "\n\t" + output + "\n\t" + line;
    }

    /**
     * Prints Welcome output.
     * @return Welcome output.
     */
    public String sendWelcome() {
        return sendOutput(WELCOME_MESSAGE);
    }

    /**
     * Takes in next command and splits it by first whitespace.
     * @return String array of size 2 split by first whitespace.
     */
    public String[] readCommand() {
        return scanner.nextLine().split(" ", 2);
    }

}
