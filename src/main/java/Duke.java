import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    /**
     * An application that reads user inputs and echoes back to them.
     * Exit the program by entering "bye".
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("POWERED BY\n" + logo);
        greet();
        String userInput = sc.next();
        while (!userInput.equals("bye")) {
            echo(userInput);
            userInput = sc.next();
        }
        exit();
    }

    /**
     * Greets the user when he/she starts the application.
     */

    private static void greet() {
        print("Hello! I'm Duke.\n\t  How can I help you?");
    }

    /**
     * Echoes the user input.
     * @param message the message to echo back to the user.
     */

    private static void echo(String message) {
        print(message);
    }

    /**
     * Says goodbye to the user when he/she exits the application.
     */

    private static void exit() {
        print("Goodbye. See you later!");
    }

    /**
     * Formats the user input or welcome/exit message and print it.
     * @param message the user input or welcome/exit message
     */

    private static void print(String message) {
        String horizLine = "\n\t____________________________________________________________\n";
        System.out.println(horizLine + "\t  " + message + horizLine);
    }
}
