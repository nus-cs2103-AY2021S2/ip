package duke.logging;

import duke.exception.InvalidInputException;
import duke.model.Task;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * A Ui class denotes a user interface system.
 */
public class Ui {
    /**
     * A welcome message from Duke chat bot.
     * @param tasks    A list of tasks in Duke chat bot.
     */
    public void showWelcome(TaskList tasks) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        tasks.list();
        System.out.println("\n     What can I do for you?");
        System.out.println("     _______________________________________\n");
    }

    /**
     * Print a straight line.
     */
    public void printLine() {
        System.out.println("     _______________________________________");
    }

    /**
     * Read the user input.
     * @param sc  A scanner to read user input.
     * @return    The user input.
     */
    public String[] readCommand(Scanner sc) {
        String[] userInput = new String[2];
        userInput[0] = sc.next();
        userInput[1] = sc.nextLine().strip();
        return userInput;
    }

    /**
     * An add command interaction which complements with the add command.
     * @param task    The task to be added to the list.
     * @param tasks   The list of tasks in Duke chat bot.
     */
    public void addCommandInteraction(Task task, ArrayList<Task> tasks) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task);
        System.out.println("     Now you have " + tasks.size() + " task(s) in the list");
    }

    /**
     * A delete command interaction which complements with the delete command.
     * @param task    The task to be added to the list.
     * @param tasks   The list of tasks in Duke chat bot.
     */
    public void deleteCommandInteraction(Task task, ArrayList<Task> tasks) {
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("     " + task);
        System.out.println("     Now you have " + tasks.size() + " task(s) in the list");
    }

    /**
     * A done command interaction which complements with the done command.
     * @param task    The task to be added to the list.
     */
    public void doneCommandInteraction(Task task) {
        System.out.println("     Nice! I've marked this task as done:\n     " + task);
    }

    /**
     * A list command interaction which complements with the list command.
     * @param task    The task to be added to the list.
     */
    public void listCommandInteraction(TaskList task) {
        task.list();
    }

    /**
     * A bye command interaction which complements with the bye command.
     */
    public void byeCommandInteraction() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    /**
     * A unknown command interaction which complements with an unknown command.
     * @throws InvalidInputException  Always throws an InvalidInputException.
     */
    public void unknownCommandInteraction() throws InvalidInputException {
        throw new InvalidInputException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
