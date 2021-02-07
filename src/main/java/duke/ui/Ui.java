package duke.ui;
import java.util.Scanner;

public class Ui {
    /**
     * Prompts the user to enter a command.
     */
    public String readCommand() {
        System.out.println("Enter a command:");
        Scanner in = new Scanner(System.in);
        String commands = in.nextLine();
        return commands;
    }

    /**
     * Prints break line.
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints the error message of a specific error.
     *
     * @param message  the error message of the error.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }


    public void showLoadingError() {
    }
}
