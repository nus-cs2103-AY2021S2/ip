package duke.customClass;

import java.util.Scanner;

/**
 * The Ui class handles the printing of the user interface and reading of user input.
 */
public class Ui {

    /**
     * A customized greeting message to be printed when the program starts.
     */
    public String greetingMessage() {
        String logo = "   ___     ___    _  _     ___     ___     _\n" +
                "  |   \\   /   \\  | \\| |   |_ _|   | __|   | |\n" +
                "  | |) |  | - |  | .` |    | |    | _|    | |__\n" +
                "  |___/   |_|_|  |_|\\_|   |___|   |___|   |____|\n" +
                "_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|\n" +
                "\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\n";
        return "Welcome back Max"
                + "\n"
                + "What can I do for you?\n";
    }

    /**
     * The line separating the user's input and the program's output.
     */
    public String separatorLine() {
        return ("____________________________________________________________");
    }

    /**
     * The message to be printed when the program exits without issues.
     */
    public String exitMessage() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Reads the user's input and returns the user's input as a string.
     * @return string of user's input.
     */
    public String readInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }
}
