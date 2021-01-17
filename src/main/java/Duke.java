import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static List<String> list;

    /**
     * An application that stores and displays user inputs.
     * Exit the program by entering "bye".
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        list = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("POWERED BY\n" + logo);
        greet();
        String userInput = sc.next();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                print(list);
            } else {
                addToList(userInput);
            }
            userInput = sc.next();
        }
        exit();
    }

    /**
     * Greets the user when he/she starts the application.
     */

    private static void greet() {
        print("Hello! I'm Duke.\n\t  How may I help you?");
    }

    /**
     * Says goodbye to the user when he/she exits the application.
     */

    private static void exit() {
        print("Goodbye. See you later!");
    }

    /**
     * Adds user input to a list.
     * @param userInput Text entered by the user.
     */

    private static void addToList(String userInput) {
        list.add(userInput);
        print("added: " + userInput);
    }

    /**
     * Prints message to user.
     * @param message Welcome/Goodbye message or a message showing what the user has added to the list.
     */

    public static void print(String message) {
        System.out.println("\n\t____________________________________________________________");
        System.out.println("\n\t  " + message);
        System.out.println("\n\t____________________________________________________________\n");
    }

    /**
     * Prints all text entered by user.
     * @param list A list of text entered by the user.
     */

    public static void print(List<String> list) {
        System.out.println("\n\t____________________________________________________________\n");
        int listCounter = 1;
        for (String userInput : list) {
            System.out.println("  \t  " + listCounter + ". " + userInput);
            listCounter++;
        }
        System.out.println("\n\t____________________________________________________________\n");
    }
}
