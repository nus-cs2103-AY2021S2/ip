package duke.logging;

import java.util.ArrayList;

import duke.exception.InvalidInputException;
import duke.model.Task;

/**
 * A Ui class denotes a user interface system.
 */
public class Ui {
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
                message += ("\n     " + task);
            }
            return message;
        }
    }

    public String scheduleCommandInteraction(ArrayList<Task> scheduledTasks) {
        if (scheduledTasks.size() == 0) {
            return "     No task scheduled";
        } else {
            String message = "    Here are your scheduled task:";
            for (Task task: scheduledTasks) {
                message += ("\n     " + task);
            }
            return message;
        }
    }
}
