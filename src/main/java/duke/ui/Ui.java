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
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public String getUserInput() {
        String userInput = in.nextLine();
        return userInput;
    }

    public void printDivider() {
        out.println(DIVIDER);
    }

    public void printGreeting() {
        String welcomeMsg = String.format("Hello! I'm\n%s\nWhat can I do for you?", LOGO);
        out.println(welcomeMsg);
    }

    public void printExitMessage() {
        out.println(EXIT_MESSAGE);
    }

    public void print(String messageForUser) {
        out.println(messageForUser);
    }
}
