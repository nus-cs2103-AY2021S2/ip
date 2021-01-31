package checklst.ui;

import java.util.Scanner;

public class Ui {
    
    private static final String WELCOME_MESSAGE = "Hello I'm Checklst! What can I do for you?";

    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void sendOutput(String output) {
        System.out.println("\t----------------------------------------");
        System.out.println("\t" + output);
        System.out.println("\t----------------------------------------");
    }

    public void sendWelcome() {
        sendOutput(WELCOME_MESSAGE);
    }

    public String[] readCommand() {
        return scanner.nextLine().split(" ", 2);
    }

}
