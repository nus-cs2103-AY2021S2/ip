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

        // Load tasks
        tasks.loadTasks();

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
        } else if (FormatChecker.isTryingToGetTaskDone(userInput)) {
            tasks.getTaskDone(userInput);
            tasks.saveTasks();
        } else if (FormatChecker.isTryingToAddTask(userInput)) {
            tasks.addTask(userInput);
            tasks.saveTasks();
        } else if (FormatChecker.isTryingToDeleteTask(userInput)) {
            tasks.deleteTask(userInput);
            tasks.saveTasks();
        } else if (FormatChecker.isTryingToGetHelp(userInput)) {
            OperationTypes.printInstructions();
        } else {
            printGetHelpMessage();
        }
        printHorizontalLine();
    }

    private static void printGetHelpMessage() {
        System.out.println("Sorry, I do not understand your command :')");
        System.out.println("If you are stuck, type \'help\' to get a list of operations available");
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
