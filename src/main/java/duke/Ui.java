package main.java;

import java.util.Scanner;

public class Ui {

    private String botName;
    private Scanner scanner;

    Ui(String botName) {
        this.botName = botName;
        this.scanner = new Scanner(System.in);
    }

    public void speak(String message) {
        System.out.println(botName + ": " + message);
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }
}
