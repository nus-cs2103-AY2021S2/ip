import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Personal Assistant Chatbot named Duke that
 * helps users to keep track of things
 * @author Damith C. Rajapakse, Jeffry Lum, Vanessa Tay
 */
public class Duke {
    public static ArrayList<String> userCommands = new ArrayList<>();

    /**
     * Greets the user and begins listening to user commands
     * @param args supplies command-line arguments to the Chatbot
     */
    public static void main(String[] args) {
        greetUser();
        listenToUserCommand();
    }

    public static void greetUser() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nHow can I help?\n");
    }

    /**
     * Takes in user's command line arguments and treats them accordingly
     * user inputs "bye", then Chatbot will end the session
     */
    public static void listenToUserCommand() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                stopListeningToCommand();
                break;
            } else if (input.equals("list")) {
                displayUserCommands();
            } else {
                addUserCommand(input);
                echoUserCommand(input);
            }
        }
    }

    /**
     * Chatbot prints out a farewell message before ending the session
     */
    public static void stopListeningToCommand() {
        System.out.println("Bye. Hope to see you soon!\n");
    }

    /**
     * Chatbot prints out what the user has just fed in as command-line input
     * @param input command-line input
     */
    public static void echoUserCommand(String input) {
        System.out.println(input + "\n");
    }

    /**
     * Chatbot adds the user's command-line input into an arraylist
     * @param input command-line input
     */
    public static void addUserCommand(String input) {
        userCommands.add(input);
        System.out.print("added: ");
    }

    /**
     * Chatbot prints out line-by-line all of the user's command-line
     * inputs in that given session
     */
    public static void displayUserCommands() {
        for(int i = 1; i <= userCommands.size(); i++) {
            System.out.println(i + ". " + userCommands.get(i-1));
        }
        System.out.println();
    }
}
