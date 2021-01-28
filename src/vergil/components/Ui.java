package vergil.components;

import java.util.Scanner;

public class Ui {
    private Scanner userInput;

    public Ui() {
        userInput = new Scanner(System.in);
    }

    public void displayWelcome() {
        System.out.println("Welcome! I'm Vergil!");
        System.out.println("How may I help you?");
        System.out.println();
    }

    public String readCommand() {
        System.out.print(">>> ");
        return userInput.nextLine();
    }

    public void displayTaskList(TaskList tl) {
        System.out.println(tl);
    }

    public void displaySuccess() {
        System.out.println("Success!");
    }

    public void displayError(String message) {
        System.out.println("Error! " + message);
    }

    public void displayBye() {
        System.out.println("Bye. See you soon!");
    }
}
