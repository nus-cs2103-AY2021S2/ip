package com.nus.duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

public class TextUi {

    public static final int INDEX_OFFSET = 1;
    /**
     * System-dependent line separator.
     */
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "===================================================";
    private static final String PREFIX = "|| ";

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    public void showWelcomeMessage() {
        printToUser(
                DIVIDER,
                "Hello! I am Duke",
                "What can I do for you my dude?",
                DIVIDER
        );
    }

    public String readUserCommand() {
        this.out.print("Enter command: ");
        return this.in.nextLine();
    }

    public void printToUser(String... messages) {
        for (String message : messages) {
            out.println(PREFIX + message.replace("\n", LS + PREFIX));
        }
    }
}
