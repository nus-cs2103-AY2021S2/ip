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
    public String showLine() {
        return "";
        //return "    ____________________________________________________________";
    }

    /**
     * Prints the error message of a specific error.
     *
     * @param message  the error message of the error.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Prints welcome message.
     */
    public String showWelcome() {
        String output = "";
        output += "    ____________________________________________________________" + "\n";
        output += "     Hello! I'm Duke" + "\n";
        output += "     What can I do for you?" + "\n";
        output += "    ____________________________________________________________" + "\n";
        return output;
    }


    public void showLoadingError() {
    }
}
