package duke;

/**
 * Handles the output from the chat bot.
 */
public class Ui {

    /**
     * Prints the response specified.
     * @param response
     */
    public void printResponse(String response) {
        System.out.println(response);
    }

    /**
     * Prints a line.
     */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a welcome message.
     */
    public void displayWelcomeMessage() {
        System.out.println("    Hello! I'm duke.Duke\n    What can I do for you?");
    }
}
