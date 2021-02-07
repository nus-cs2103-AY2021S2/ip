package yoda.ui;

import yoda.task.Task;
import yoda.task.TaskList;

import java.util.Scanner;

/**
 * Ui class to handle interactions with the user.
 */
public class Ui {
    /** Divider to divide between successive instructions */
    private static final String DIVIDER = "--------------------------------";
    /** Valid inputs that are available to the user */
    private static final String HELP_LIST = "HELP!";
    /** Scanner to scan user input */
    private Scanner s;

    /**
     * Creates a Ui object.
     */
    public Ui() {
        s = new Scanner(System.in);
    }

    /**
     * Prints the divider.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Greets the user when the Yoda chatbot is started up.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you?");
    }

    /**
     * Reads the input provided by the user on the command line.
     * @return Input provided by the user.
     */
    public String readInput() {
        return s.nextLine();
    }

    /**
     * Bids farewell to the user after user is done using the Yoda chatbot.
     */
    public String exit() {
        return "See you soon, I will!";
    }


    public String showError() {
        return "The greatest teacher, failure is \n"
                + "Recognise your request, I do not"
                + showHelp();
    }

    public String showHelp() {
        return HELP_LIST;
    }

    public String printAddedTask(Task task) {
        return "Do or do not. There is no try.\n"
                + "Added this task to the list, I have:\n" + task;
    }

    public String printDeletedTask(Task task, int numberOfTasksLeft) {
        return "Deleted this task, I have:\n" + task
                + "\n" + numberOfTasksLeft + " in the list, you have left!";
    }

    public String printFinishedTask(Task task, int numberOfTasksLeft) {
        return "Well done my young Padawan!\n"
                + "Marked this task as done, I have:\n"
                + task
                + "\n" + numberOfTasksLeft + " in the list, you have left!";
    }

    public String printTasks(String identifier, String list) {
        switch(identifier) {
        case "all":
            return "All the tasks in your list, I present to you\n"
                    + list;
        case "find":
            return "The matching tasks in your list, I present to you\n"
                    + list;
        case "deadline":
            return "The deadline(s) in your list, I present to you\n"
                    + list;
        case "event":
            return "The event(s) in your list, I present to you\n"
                    + list;
        case "todo":
            return "The todo(s) in your list, I present to you\n"
                    + list;
        case "bad":
            return "To get a specific type of task, use list -d/-t/-e.\n"
                    + "Else, the find request, you can use instead";
        default:
            return "The greatest teacher failure is.";
        }
    }
}
