package duke.logging;

import java.util.ArrayList;

import duke.exception.InvalidInputException;
import duke.model.Task;

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
     * @return       A straight line.
     */
    public String printLine() {
        return "     _______________________________________";
    }

    /**
     * An add command interaction which complements with the add command.
     * @param task    The task to be added to the list.
     * @param tasks   The list of tasks in Duke chat bot.
     * @return        The message replied by Duke chat bot.
     */
    public String addCommandInteraction(Task task, ArrayList<Task> tasks) {
        return "     Got it. I've added this task:\n     " + task + "\n     Now you have "
                + tasks.size() + " task(s) in the list";
    }

    /**
     * A delete command interaction which complements with the delete command.
     * @param task     The task to be added to the list.
     * @param tasks    The list of tasks in Duke chat bot.
     * @return         The message replied by Duke chat bot.
     */
    public String deleteCommandInteraction(Task task, ArrayList<Task> tasks) {
        return "     Noted. I've removed this task:\n     " + task + "\n     Now you have "
                + tasks.size() + " task(s) in the list";
    }

    /**
     * A done command interaction which complements with the done command.
     * @param task    The task to be added to the list.
     * @return        The message replied by Duke chat bot.
     */
    public String doneCommandInteraction(Task task) {
        return "     Nice! I've marked this task as done:\n     " + task;
    }

    /**
     * A bye command interaction which complements with the bye command.
     */
    public String byeCommandInteraction() {
        return "     Bye. Hope to see you again soon!";
    }

    /**
     * A unknown command interaction which complements with an unknown command.
     * @throws InvalidInputException  Always throws an InvalidInputException.
     */
    public String unknownCommandInteraction() throws InvalidInputException {
        throw new InvalidInputException("     OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * A find command interaction which complements with a find command.
     * @param matchingTasks    All the matching tasks found.
     * @return        The message replied by Duke chat bot.
     */
    public String findCommandInteraction(ArrayList<Task> matchingTasks) {
        if (matchingTasks.size() == 0) {
            return "     No match found";
        } else {
            String message = "     Here are the matching tasks in your list:";
            for (Task task: matchingTasks) {
                message += ("     " + task);
            }
            return message;
        }
    }
}
