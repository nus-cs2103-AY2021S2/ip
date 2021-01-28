package vergil.components;

import java.util.Scanner;

public class Ui {
    private Scanner userInput;

    public Ui() {
        userInput = new Scanner(System.in);
    }

    /**
     * Displays a welcome message.
     */
    public void displayWelcome() {
        System.out.println("Welcome! I'm Vergil!");
        System.out.println("How may I help you?");
        System.out.println();
    }

    /**
     * Reads the user's command input, and returns it as a String.
     * @return the user's command input.
     */
    public String readCommand() {
        System.out.print(">>> ");
        return userInput.nextLine();
    }

    /**
     * Displays the contents of the given task list.
     * @param tl the task list whose contents are to be displayed.
     */
    public void displayTaskList(TaskList tl) {
        System.out.println(tl);
    }

    /**
     * Displays a success message.
     */
    public void displaySuccess() {
        System.out.println("Success!");
    }

    /**
     * Displays an error message stating the error.
     * @param message a string describing the actual error.
     */
    public void displayError(String message) {
        System.out.println("Error! " + message);
    }

    public void displayFindResult(TaskList resultsList) {
        if (resultsList.getLength() > 0) {
            System.out.println("Here's what I found: ");
            displayTaskList(resultsList);
        } else {
            System.out.println("Oops! I couldn't find any task with the given keyword.");
        }
    }

    /**
     * Displays a farewell message.
     */
    public void displayBye() {
        System.out.println("Bye. See you soon!");
    }
}
