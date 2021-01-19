package duke.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import duke.command.CommandOption;

public class Duke {
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

    private static ArrayList<String> storage = new ArrayList<>();

    /**
     * Main class for project duke.
     * Takes in an user command (within the exclusive list) and react accordingly.
     * @param args normal input as per other Java programmes
     */
    public static void main(String[] args) {
        System.out.println(parseMessage(greet));
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        while(!message.equals("bye")) {
            printReply(message);
            message = sc.nextLine();
        }
        System.out.println(parseMessage(exit));
    }

    /**
     *  Disclaimer: the idea of using .valueOf and convert to UpperCase is inspired
     *      based on discussion of #Issue 14 in forum.
     *      Credit to @samuelfangjw who mentioned it first
     */
    private static void printReply(String message) {
        try {
            CommandOption command = CommandOption.valueOf(message.toUpperCase(Locale.ROOT));
            switch (command) {
                case LIST:
                    System.out.println(parseMessage(storage));
                    break;
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println(parseMessage(new String[]{"added: " + message}));
            storage.add(message);
        }
    }

    private static String parseMessage(String[] messages) {
        String border = "    ____________________________________________________________\n";
        StringBuilder res = new StringBuilder(border);
        String indent = "     ";
        for (String message : messages) {
            res.append(indent).append(message).append("\n");
        }
        res.append(border);
        return res.toString();
    }

    private static String parseMessage(List<String> messages) {
        String border = "    ____________________________________________________________\n";
        StringBuilder res = new StringBuilder(border);
        String indent = "     ";
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i);
            int index = i + 1;
            res.append(indent).append(index + ".").append(message).append("\n");
        }
        res.append(border);
        return res.toString();
    }
}
