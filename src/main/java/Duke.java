import java.util.Scanner;

/**
 * Represents a Personal Assistant Chatbot named Duke that
 * helps users to keep track of things
 * @author Damith C. Rajapakse, Jeffry Lum, Vanessa Tay
 */
public class Duke {

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
     * Takes in and echoes user's command line arguments until
     * user inputs "bye", then Chatbot will end the session
     */
    public static void listenToUserCommand() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                stopListeningToCommand();
                break;
            } else {
                echoUserCommand(input);
            }
        }
    }

    public static void stopListeningToCommand() {
        System.out.println("Bye. Hope to see you soon!\n");
    }

    public static void echoUserCommand(String input) {
        System.out.println(input + "\n");
    }
}
