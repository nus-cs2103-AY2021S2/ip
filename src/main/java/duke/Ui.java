package duke;

import java.util.Scanner;

/**
 * Representation of the UI of Duke
 */
public class Ui {
    private static final String EXIT_MESSAGE = "I hope I have been of assistance. Goodbye. C:";
    private static final String LOAD_SUCCESS = "Saved data found and successfully loaded.";
    private static final String WELCOME_MESSAGE = "Greetings. My name is I-01B, but you may call me DUKE. \n"
            + "What can I assist you with?";
    private static final String ERROR_HEADER = "Error: ";
    public Ui() {
    }

    /**
     * Reads the next user given command.
     *
     * @return Command entered by the user.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints the LoadSuccess message to output.
     */
    public void showLoadSuccess() {
        System.out.println(LOAD_SUCCESS);
    }

    /**
     * Returns the Welcome message as a string
     */
    public String getWelcome() {
        return WELCOME_MESSAGE;
    }

    /**
     * Prints the Exit message to output.
     */
    public void showExit() {
        System.out.println(EXIT_MESSAGE);
    }
    /**
     * Returns the error as a string.
     *
     * @param e error to be printed.
     */
    public String toErrorString(Throwable e) {
        return ERROR_HEADER + e.toString();
    }

    /**
     * Returns the given message.
     *
     * @param message String to be printed.
     */
    public String print(String message) {
        return message;
    }
}
