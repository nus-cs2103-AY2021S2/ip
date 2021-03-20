package duke.ui;

import java.util.Scanner;

public class Ui {
    /**
     * Prompts the user to enter a command.
     */
    public static String readCommand() {
        System.out.println("Enter a command:");
        Scanner in = new Scanner(System.in);
        String commands = in.nextLine();
        return commands;
    }

    /**
     * Displays default welcome message to users.
     */
    public static String showHelpMessage() {
        String welcomeMessage = "Duke User Guide: \n";
        welcomeMessage += "1. `list` - List all the tasks \n";
        welcomeMessage += "2. `todo` - Create a ToDO task \n (e.g. `todo CS2103 ip`) \n";
        welcomeMessage += "3. `deadline` - Create a Deadline task \n (e.g. `deadline CS2103 Quiz /by 2021-02-19`) \n";
        welcomeMessage += "4. `event` - Create an Event task \n (e.g. `event CS2103 Lecture /at 2021-02-19`) \n";
        welcomeMessage += "5. `done` - Mark a task as done \n (e.g. `done 3`) \n";
        welcomeMessage += "6. `delete` - Delete a task \n (e.g. `delete 3`) \n";
        welcomeMessage += "7. `find` - Find a task \n (e.g. `find CS2103`) \n";
        welcomeMessage += "8. `bye` - Quit the program \n";
        welcomeMessage += "9. `help` - See the user guide \n";
        return welcomeMessage;
    }

    /**
     * Displays default goodbye message to users.
     */
    public static String showGoodbyeMessage() {
        return "Bye!";
    }

    public static String showNewLine() {
        return "\n";
    }

    /**
     * Prints break line.
     */
    public String showLine() {
        return "";
    }

    /**
     * Prints the error message of a specific error.
     *
     * @param message the error message of the error.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Prints the error message when the user types a non-existing command.
     *
     */
    public static String showWrongCommandMessage() {
        String wrongCommandMessage = "OOPS!!! I'm sorry, but I don't know what that means :-( \n\n";
        wrongCommandMessage += "Enter 'help' to see all the commands.";
        return wrongCommandMessage;
    }

}
