import java.util.Scanner;


/**
 * Main class for the application
 */
public class Lihua {
    private static final String EXIT_SIGNAL = "bye";
    private static Todos todos = new Todos();

    public static void main(String[] args) {
        // Initialization phase
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
                actionUpon(userInput);
                userInput = sc.nextLine();
            }
        }
    }

    /**
     * Action upon the user input.
     * E.g. Store the user input to the list, show the items in the list
     * @param userInput user input string which is not the exit signal
     */
    private static void actionUpon(String userInput) {
        printHorizontalLine();
        if (userInput.equals("list")) {
            todos.printList();
        } else {
            todos.addItem(userInput);
            System.out.println(String.format("Added: %s", userInput));
        }
        printHorizontalLine();
    }

    private static void printHello() {
        String welcome = "Hello! My name is Lihua.\n"
                + "What can I do for you today?";
        printHorizontalLine();
        System.out.println(welcome);
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
