import java.util.Scanner;

/**
 * Duke is a CLI chat-bot for task tracking.
 *
 * Duke is a chat-bot that assists users in tracking their To-Do's, events, and deadlines via the Command Line.
 * @version 0.1
 * @author Douglas Wei Jing Allwood
 * @author douglas_allwood@u.nus.edu
 */
public class Duke {
    public static void main(String[] args) {
        // Greet the user
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        // Define the termination string
        final String termination_str = "bye";
        Scanner scanner = new Scanner(System.in);


        // Get user input
        while (true) {
            String input = scanner.nextLine();
            if (input.equals(termination_str)) {
                System.out.println("Bye, hope to see you again!");
                break;  // Exit program
            }
            System.out.println(input);
        }
        scanner.close();
    }
}
