package duke.ui;

import java.io.PrintStream;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "------------------------------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        in = new Scanner(System.in);
        out = System.out;
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void printDivider() {
        out.println(DIVIDER);
    }

    public void printGreeting() {
        String welcomeMsg = String.format("Hello! I'm\n%s\nWhat can I do for you?", LOGO);
        out.println(welcomeMsg);
    }

    public void printExitMessage() {
        out.println(MESSAGE_EXIT);
    }

    public void print(String messageForUser) {
        out.println(messageForUser);
    }
}
