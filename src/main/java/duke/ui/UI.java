package duke.ui;

import java.util.Scanner;

public class UI {
    private static final String[] greet = {
            " ____        _        ",
            "|  _ \\ _   _| | _____ ",
            "| | | | | | | |/ / _ \\",
            "| |_| | |_| |   <  __/",
            "|____/ \\__,_|_|\\_\\___|\n",
            "Greetings! I'm Your Personal Assistant Duke:)",
            "What can I do for you today?"
    };

    private static final String[] exit = {
            "Bye. Nice to meet you and hope to see you again soon!"
    };

    private static final String border =
            "    ____________________________________________________________" +
                    "_______________\n";

    private static final String indent = "     ";

    private final Scanner in;

    public UI() {
        this.in = new Scanner(System.in);
    }

    /**
     * Returns the formatted message to be printed.
     * @param messages an array of strings, main body of the message to be formatted.
     * @return the formatted message (specifically, greet and bye) to be printed.
     */
    public static String formatMessage(String[] messages) {
        StringBuilder res = new StringBuilder(border);
        for (String message : messages) {
            res.append(indent).append(message).append("\n");
        }
        res.append(border);
        return res.toString();
    }

    public static void greet() {
        System.out.println(formatMessage(greet));
    }

    public static void bye() {
        System.out.println(formatMessage(exit));
    }

    private static void printReply(String msg) {
        System.out.println(msg);
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public static void printMessage(String[] msg) {
        printReply(formatMessage(msg));
    }
}
