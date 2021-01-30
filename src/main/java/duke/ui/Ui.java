package duke.ui;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Handles the User Interface of Duke
 */
public class Ui {
    private Scanner sc;

    public Ui(Scanner sc) {
        this.sc = sc;
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void close() {
        sc.close();
    }

    /**
     * Prints the welcome message
     */
    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetingMessage = "Hello! I'm a Chat bot and my name "
                + "is Joe" + "\nHow may I help you?";
        System.out.println(formatMessage(logo + "\n" + greetingMessage));
    }

    /**
     * Prints custom message when tasks are added
     * @param task
     */
    public static void printTaskAddedMessage(Task task) {
        System.out.println(formatMessage("Got it. I've added this task:\n"
                + task + "\n" + Task.getNumOfTasksString()));
    }

    /**
     * Prints custom message when tasks are removed
     * @param task
     */
    public static void printTaskRemovedMessage(Task task) {
        System.out.println(formatMessage("The following task has been removed:\n"
                + task + "\n" + Task.getNumOfTasksString()));
    }

    /**
     * Prints custom message when tasks are set to done
     * @param task
     */
    public static void printTaskDoneMessage(Task task) {
        System.out.println(formatMessage("You have completed the following task:\n"
                + task + "\n" + "Keep up the good work!"));
    }

    /**
     * Prints custom error message when an invalid command is received
     */
    public static void printInvalidCommandMessage() {
        System.out.println(formatMessage("Please enter a valid command! \n"
                + "Type help for a list of commands"));
    }

    /**
     * Prints custom message when Duke is shutting down
     */
    public static void printDukeExitMessage() {
        System.out.println(formatMessage("Thanks for using Duke, have a great day ahead!"));
    }

    public static void printMatchingList(List<Task> taskList) {
        String taskListString = "Here are the matching results on your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            String taskString = (i + 1) + ". " + taskList.get(i);
            taskListString = taskListString + taskString
                    + (i == taskList.size() - 1 ? "" : "\n");
        }
        System.out.println(formatMessage(taskListString));
    }

    /**
     * Prints information to user
     * @param message the message to print
     */
    public static void printMessage(String message) {
        System.out.println(formatMessage(message));
    }

    /**
     * Prints error message information to user
     * @param errorMessage the error message to print
     */
    public static void printError(String errorMessage) {
        System.out.println(formatMessage("Error: " + errorMessage));
    }

    private static String formatMessage(String str) {
        return "____________________________________________________________"
                + "\n" + str + "\n"
                + "____________________________________________________________\n";
    }
}
