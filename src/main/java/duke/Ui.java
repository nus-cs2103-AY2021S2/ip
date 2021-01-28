package duke;

import java.util.Scanner;

public class Ui {
    public Ui() {
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showLoadSuccess() {
        System.out.println("Saved data found and successfully loaded.");
    }

    public void showWelcome() {
        System.out.println("Greetings. My name is I-01B, but you may call me DUKE.");
        System.out.println("What can I assist you with?");
    }

    public void showError(Throwable e) {
        System.out.println("Error: " + e);
    }

    public void print(String message) {
        System.out.println(message);
    }
}
