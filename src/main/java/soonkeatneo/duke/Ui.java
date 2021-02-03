package soonkeatneo.duke;

/**
 * Implementation for the chat-bot UI.
 * @author Soon Keat Neo
 * @version CS2103T AY20/21 Sem 1 iP
 */

public class Ui {
    private static final String SEPARATORS = "~~~~~~~~~~~~~~~~~~~~~~";

    public Ui() {
        greetUser();
    }

    /**
     * Prints separators providing for clarity of on-screen text.
     */
    public static void printSeparators() {
        System.out.println(SEPARATORS);
    }

    /**
     * Print the requested message in the bot's formatting.
     * @param message The message to be printed
     */
    public static void printMessage(String message) {
        System.out.println("    " + message);
    }

    /**
     * Greets user with a message on the screen when the function is called.
     */
    public static void greetUser() {
        Ui.printMessage("Henlooooo~! My name is " + Duke.BOT_NAME);
        Ui.printMessage("What can I do for you today? :)");
    }
}
