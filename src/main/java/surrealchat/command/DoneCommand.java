package surrealchat.command;

import java.util.NoSuchElementException;

import surrealchat.task.Task;
import surrealchat.task.TaskManagement;

/**
 * Command object for toggling the isDone variable on Task object.
 */
public class DoneCommand extends Command {
    private static final int TASK_DONE = 1;
    private static final int TASK_NOT_DONE = 0;
    protected final String taskNumberString;

    /**
     * Creates a new DoneCommand object.
     * @param taskNumberString Index number of Task for which to toggle done/undone.
     */
    public DoneCommand(String taskNumberString) {
        super("done");
        this.taskNumberString = taskNumberString;
    }

    private int getInputNumber(String description) {
        description = description.trim();
        if (description.isEmpty()) {
            throw new NoSuchElementException(
                    "Did you forget to put a number for the command you just typed in? Not stonks!\n");
        } else {
            try {
                return Integer.valueOf(description);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(
                        "Did you put something other than a number or did you put a number incorrectly? Not stonks!\n");
            }
        }
    }

    private String printOutput(Task task) {
        String outputString = "";
        if (task.getStatusInt() == DoneCommand.TASK_DONE) {
            outputString += "Stonks! You've done this task:\n";
        } else if (task.getStatusInt() == DoneCommand.TASK_NOT_DONE) {
            outputString += "Not stonks! This task has been marked as undone:\n";
        } else {
            throw new IllegalArgumentException("Incorrect task done number! It should be 1 or 0. Not stonks!\n");
        }
        outputString += String.format("%s\n", task);
        return outputString;
    }

    /**
     * Executes done command to toggle isDone variable on Task object.
     * @param taskManagement TaskManagement object where Task object to be marked done/undone is stored.
     * @return String upon successful toggling of Task.
     */
    public String execute(TaskManagement taskManagement) {
        try {
            int taskNumber = this.getInputNumber(this.taskNumberString);
            assert taskNumber > 0 : "Invalid task number. Not stonks!\n";
            assert taskNumber <= taskManagement.getNumberOfTasks() : "Invalid task number. Not stonks!\n";

            Task doneTask = taskManagement.markAsDone(taskNumber);
            return printOutput(doneTask);
        } catch (Throwable e) {
            return e.getMessage();
        }
    }

    /**
     * Describes usage of done command.
     * @return String describing the done command.
     */
    public static String displayHelp() {
        String outputString = "Given a task number, toggles whether task is done/undone.\n";
        outputString += "Format of arguments: done [task number]\n";
        return outputString;
    }
}
