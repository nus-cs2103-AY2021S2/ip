package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private static final String DIVIDER_LINE = "____________________________________________________________";
    private static final String LOGO = "                            _       \n" +
            "                           (_)      \n" +
            " _ __ ___   __ _ _ ____   ___ _ __  \n" +
            "| '_ ` _ \\ / _` | '__\\ \\ / / | '_ \\ \n" +
            "| | | | | | (_| | |   \\ V /| | | | |\n" +
            "|_| |_| |_|\\__,_|_|    \\_/ |_|_| |_|";
    private static final String WELCOME_MESSAGE = "Hello! I'm Marvin what can I do for you today?";
    private static final String LOADING_ERROR_MESSAGE = "Sorry was unable to load tasks from storage!";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(DIVIDER_LINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println(LOGO);
        System.out.println(WELCOME_MESSAGE);
        showLine();
    }

    public String readCommand() {
        System.out.print(">>> ");
        return scanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println(LOADING_ERROR_MESSAGE);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void show(String message) {
        System.out.print(message);
    }

    public void showNewLine(String message) {
        System.out.println(message);
    }
}
