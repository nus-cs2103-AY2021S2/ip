package duke.main;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import duke.command.CommandOption;

/**
 * Main class for project duke.
 * Takes in an user command (within the exclusive list) and react accordingly.
 */
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
    private static final String border =
            "    ____________________________________________________________\n";
    private static final String indent = "     ";

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(parseMessage(greet));
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        while(!message.equalsIgnoreCase("bye")) {
            printReply(message);
            message = sc.nextLine();
        }
        System.out.println(parseMessage(exit));
    }

    /**
     * 1. Takes in the first word from user input and carries out relevant actions based on
     *      the word by printing out corresponding replies.
     * 2. A command is NOT case sensitive.
     *      For example, "LIST"/"list"/"List" will have the same effect.
     * 3. However, no additional whitespaces should be entered.
     *      For example, "LIST "/"list "/"List " will not work.
     * 4. Disclaimer: the idea of using .valueOf and convert to UpperCase is inspired
     *      based on discussion of #Issue 14 in forum.
     *      Credit to @samuelfangjw who mentioned it first.
     * @param message first (String) word from user input
     */
    private static void printReply(String message) {
        String[] msgArray = message.split(" ", 2);
        String commandWord = msgArray[0];
        String otherInfo = null;
        if (msgArray.length > 1) {
            otherInfo = msgArray[1];
        }

        System.out.print(border);
        try {
            CommandOption command = CommandOption.valueOf(commandWord.toUpperCase(Locale.ROOT));
            switch (command) {
                case LIST:
                    printList();
                    break;
                case DONE:
                    completeTask(otherInfo);
                    break;
            }
        }
        catch (IllegalArgumentException e) {
            addTask(message);
        }
        System.out.println(border);
    }

    /**
     * Returns the formatted message (specifically, greet and bye) to be printed.
     * @param messages an array of strings, main body of the message to be formatted.
     * @return the formatted message (specifically, greet and bye) to be printed.
     */
    private static String parseMessage(String[] messages) {
        StringBuilder res = new StringBuilder(border);
        for (String message : messages) {
            res.append(indent).append(message).append("\n");
        }
        res.append(border);
        return res.toString();
    }

    private static void printList() {
        StringBuilder res = new StringBuilder();
        res.append(indent)
                .append("Hi! This is your todo list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            res.append(indent)
                    .append(i+1 + ".")
                    .append("[" + task.getStatusIcon() + "] ")
                    .append(task.getDescription())
                    .append("\n");
        }
        System.out.print(res.toString());
    }

    /**
     * Complete the task with the given index and print the confirmation message.
     *
     * Two possible errors are handled. Namely, they are:
     *      1. taskIndex is not an integer;
     *      2. taskIndex is out of bound.
     * @param taskIndex taskIndex from user input, in String.
     */
    private static void completeTask(String taskIndex) {
        int index;
        Task task;
        try{
            index = Integer.parseInt(taskIndex) - 1;
        }
        catch (NumberFormatException e) {
            System.out.println("Task index entered is not an integer");
            return;
        }

        try {
            task = tasks.get(index);
            task.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task index is out of bound");
            return;
        }

        String res = indent +
                "Wonderful! You have completed this task:\n" +
                indent +
                "  [" + task.getStatusIcon() + "] " +
                task.getDescription();
        System.out.println(res);
    }

    private static void addTask(String taskDescription) {
        Task task = new Task(taskDescription);
        tasks.add(task);

        String res = indent +
                "Added: " +
                taskDescription;
        System.out.println(res);
    }
}
