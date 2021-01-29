package surrealchat.user;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Processes inputs given by the user.
 */
public class UserInput {
    protected Scanner sc;

    /**
     * Creates a new UserInput instance.
     * @param sc Scanner object used for input processing.
     */
    public UserInput(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Advances scanner to next line.
     */
    public void scannerNextLine() {
        this.sc.nextLine();
    }

    /**
     * Closes the scanner at the end of code execution.
     */
    public void closeScanner() {
        this.sc.close();
    }

    /**
     * Obtains the first one-word command from input line.
     * @return Command for Meme Man.
     */
    public String getInputCommand() {
        String command = this.sc.next();
        return command;
    }

    /**
     * Obtains the task description from remainder of input line.
     * @return Description used to create task.
     */
    public String getInputDescription() {
        String taskDescription = this.sc.nextLine();
        return taskDescription; //Note: Every task description comes with a space
    }

    /**
     * Obtains number from a given description.
     * @param description - The raw description.
     * @return The number to find task in the list.
     */
    public int getInputNumber(String description) {
        description = description.trim();
        if (description.isEmpty()) {
            throw new NoSuchElementException(
                    "Did you forget to put a number for the command you just typed in? Not stonks!");
        } else {
            try {
                int taskNumber = Integer.valueOf(description);
                return taskNumber;
            } catch (NumberFormatException e) {
                throw new NumberFormatException(
                        "Did you put something other than a number or did you put a number incorrectly? Not stonks!");
            }
        }
    }

    /**
     * Checks for any excess arguments in commands that should not have arguments.
     */
    public void checkExcessArguments() {
        String excess = this.sc.nextLine();
        if (!excess.isEmpty()) {
            throw new InputMismatchException("Excessive inputs for a no-input command. Not stonks!");
        }
    }
}
