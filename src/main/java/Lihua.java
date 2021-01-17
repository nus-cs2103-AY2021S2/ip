import java.util.Scanner;

/**
 * Main class for the application
 */
public class Lihua {
    private static final String EXIT_SIGNAL = "bye";
    private static Tasks tasks = new Tasks();

    public static void main(String[] args) {
        // Initialization phase
        Scanner sc = new Scanner(System.in);

        // Print out welcome message
        printHello();

        // Get user input, perform operations on it
        // If the input received is the exit signal, the application exits
        String userInput = sc.nextLine();
        while (true) {
            if (userInput.equals(EXIT_SIGNAL)) {
                printGoodbye();
                return;
            } else if (!userInput.equals("")) {
                actionUpon(userInput);
            }
            userInput = sc.nextLine();
        }
    }

    /**
     * Action upon the user input.
     * E.g. Add a new task to the list, show the tasks in the list
     * @param userInput user input string, which is not the exit signal
     */
    private static void actionUpon(String userInput) {
        printHorizontalLine();
        if (FormatChecker.isPrintingList(userInput)) {
            tasks.printList();
        } else if (FormatChecker.isGettingTaskDone(userInput)) {
            Integer index = Integer.valueOf(userInput.split(" ")[1]);
            tasks.getTaskDone(index);
        } else if (FormatChecker.isAddingTask(userInput)) {
            tasks.addTask(userInput);
        } else {
            // Dummy case, to be modified
            System.out.println("Sorry, I do not understand your command :')");
        }
        printHorizontalLine();
    }

    private static void printHello() {
        printHorizontalLine();
        String welcome = "Hello! My name is Lihua.\n"
                + "What can I do for you today? (=~ω~=)";
        System.out.println(welcome);
        printHorizontalLine();
    }

    private static void printGoodbye() {
        printHorizontalLine();
        System.out.println("Goodbye! Hope to see you again soon! (=~ω~=)");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("-------------------------------------------------");
    }
}
