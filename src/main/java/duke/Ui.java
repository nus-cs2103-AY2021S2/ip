package duke;

import java.util.Scanner;

/**
 * Representation of the UI of Duke
 */
public class Ui {
    public Ui() {
    }

    /**
     * Reads the next user given command.
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
        System.out.println("Saved data found and successfully loaded.");
    }

    /**
     * Prints the Welcome message to output.
     */
    public String showWelcome() {
        return "Greetings. My name is I-01B, but you may call me DUKE. \n" +
          "What can I assist you with?";
    }

    /**
     * Prints the error to output.
     * @param e error to be printed.
     */
    public String showError(Throwable e) {
        return "Error: " + e.toString();
    }

    /**
     * Prints the given message to output.
     * @param message String to be printed.
     */
    public String print(String message) {
        return message;
    }
}
