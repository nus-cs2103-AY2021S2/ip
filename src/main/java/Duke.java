import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main implementation for the Duke chat-bot.
 * @author Soon Keat Neo
 * @version CS2103T AY20/21 Sem 1 iP v0.1
 */

public class Duke {
    /** Allows for easy change of the bot name in future. **/
    final static String BOTNAME = "DukeNukem";
    final static String SEPARATORS = "~~~~~~~~~~~~~~~~~~~~~~";
    public static void main(String[] args) {
        greetUser();
        listenInput();
        quit();
    }

    /**
     * Greets user with a message on the screen when the function is called.
     */
    public static void greetUser() {
        String greetingString = "   Henlooooo~! My name is " + BOTNAME + "!\n" +
                "   What can I do for you today? :)";
        System.out.println(greetingString);
    }

    /**
     * Prints separators providing for clarity of on-screen text.
     */
    public static void printSeparators() {
        System.out.println(SEPARATORS);
    }

    /**
     * Listens to the user's input and echoes it back.
     */
    public static void listenInput() {
        Scanner scannerObject = new Scanner(System.in);
        boolean stillListening = true;
        String inputString;
        while (stillListening) {
            printSeparators();
            inputString = scannerObject.nextLine();
            printSeparators();
            if (inputString.trim().equals("bye")) {
                quit();
                stillListening = false;
                continue;
            }
            System.out.println("    " + inputString);
        }

    }

    /**
     * Quits the program and provides provisions for clean-up.
     */
    public static void quit() {
        System.out.println("    Hope you had an enjoyable experience! Good-bye~");
        System.exit(0);
    }

}
