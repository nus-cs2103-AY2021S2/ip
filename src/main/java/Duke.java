import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main implementation for the Duke chat-bot.
 * @author Soon Keat Neo
 * @version CS2103T AY20/21 Sem 1 iP v0.1
 */

public class Duke {
    /** Allows for easy change of the bot name in future. **/
    final private static String BOTNAME = "DukeNukem";
    final private static String SEPARATORS = "~~~~~~~~~~~~~~~~~~~~~~";
    static private List<String> taskList = new ArrayList<>();
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
     * Listens to the user's input, adds to list or prints appropriately.
     */
    public static void listenInput() {
        Scanner scannerObject = new Scanner(System.in);
        boolean stillListening = true;
        String inputString;
        while (stillListening) {
            printSeparators();
            inputString = scannerObject.nextLine();
            printSeparators();
            switch (inputString.trim()) {
                case "list":
                    printList();
                    break;
                case "bye":
                    quit();
                    stillListening = false;
                    break;
                default:
                    taskList.add(inputString);
                    System.out.println("    added: " + inputString);
            }
        }

    }

    public static void printList() {
        System.out.println("    List: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + taskList.get(i));
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
