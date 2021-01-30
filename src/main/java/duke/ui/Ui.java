package duke.ui;

import duke.storage.Storage;
import duke.task.Task;

import java.util.Scanner;

/**
 * Ui Class that handles passing output
 */
public class Ui {
    private final String name = "Rawrz";
    private Scanner scanner;

    /**
     * Constructor for Ui. Creates a Scanner with System.in
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns the input given
     * @return String input passed into System
     */
    public String readCommand() {
        return scanner.nextLine();
    }


    private void showLine() {
        String border = "___";
        System.out.println(border);
    }

    /**
     * Shows the introduction message to the user.
     */
    public void showIntro() {
        showLine();
        System.out.println("   Hello there! I'm " + name + ", always here for you!\n   How can I help you today?");
        showLine();
    }

    /**
     * Shows the goodbye message to the user.
     */
    public void showGoodbye() {
        showLine();
        System.out.println("   Bye! Hope to see you again! Rawrz! :)");
        showLine();
    }

    /**
     * Shows the error message to the user.
     * @param error This is the String error message.
     */
    public void showError(String error) {
        showLine();
        System.out.println("   " + error);
        showLine();
    }


    /**
     * Shows the remove message to the user.
     * @param task This is the task that is removed
     * @param noTasks This is the number of tasks left after removal.
     */
    public void showRemove(Task task, int noTasks) {
        showLine();
        System.out.println("   Okay! I've removed this task:");
        System.out.println("      " + task);
        System.out.println("   Now you have " +noTasks + " tasks in the list");
        showLine();
    }

    /**
     * Shows the list of tasks stored in the chatbot to the user
     * @param storage This is the Storage that contains the list of Tasks stored.
     */
    public void showList(Storage storage) {
        showLine();
        for (int i = 0; i < storage.getArrSize(); i++) {
            System.out.println("   " + (i + 1) + ". " + storage.get(i));
        }
        showLine();
    }

    /**
     * Shows the done message to the user.
     * @param task This is the task that has been completed.
     */
    public void showDone(Task task) {
        showLine();
        System.out.println("   Nice! I've marked this task as done:");
        System.out.println("      " + task);
        showLine();
    }

    /**
     * Shows the add task message to the user.
     * @param task This is the task that is added.
     * @param noTasks This is the number of tasks that are now in the system.
     */
    public void showAdd(Task task, int noTasks) {
        showLine();
        System.out.println("   Got it! I've added this task:\n      " + task);
        System.out.println("   Now you have " + noTasks + " tasks in the list");
        showLine();
    }
}
