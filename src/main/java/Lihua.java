import java.util.Scanner;


/**
 * Main class for the application
 */
public class Lihua {
    private static final String EXIT_SIGNAL = "bye";

    public static void main(String[] args) {
        // Initialize new Scanner object to receive input
        Scanner sc = new Scanner(System.in);

        // Print out welcome message
        printHello();

        // Get user input, and echo the input
        // If the input is the exit signal, exit the application
        String userInput = sc.nextLine();
        while (true) {
            if (userInput.equals(EXIT_SIGNAL)) {
                printGoodbye();
                return;
            } else {
                printEchoMessage(userInput);
                userInput = sc.nextLine();
            }
        }
    }

    private static void printHello() {
        String welcome = "Hello! My name is Lihua.\n"
                + "What can I do for you today?";
        printHorizontalLine();
        System.out.println(welcome);
        printHorizontalLine();
    }

    private static void printEchoMessage(String message) {
        printHorizontalLine();
        System.out.println(String.format("Your input command: %s", message));
        printHorizontalLine();
    }

    private static void printGoodbye() {
        printHorizontalLine();
        System.out.println("Goodbye! Hope to see you again soon!");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("------------------------------------------------");
    }
}
