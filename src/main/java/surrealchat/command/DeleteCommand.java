package surrealchat.command;

import java.util.NoSuchElementException;

import surrealchat.task.Task;
import surrealchat.task.TaskManagement;

/**
 * Command object for deleting a Task object.
 */
public class DeleteCommand extends Command {
    protected final String taskNumberString;

    /**
     * Creates new DeleteCommand object.
     * @param taskNumberString Index number of Task to be deleted.
     */
    public DeleteCommand(String taskNumberString) {
        super("delete");
        this.taskNumberString = taskNumberString;
    }

    private int getInputNumber(String description) {
        description = description.trim();

        if (description.isEmpty()) {
            throw new NoSuchElementException(
                    "Did you forget to put a number for the command you just typed in? Not stonks!\n");
        }

        try {
            return Integer.valueOf(description);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(
                    "Did you put something other than a number or did you put a number incorrectly? Not stonks!\n");
        }
    }

    private boolean isInvalidTaskNumber(int taskNumber, TaskManagement taskManagement) {
        return ((taskNumber <= 0) || (taskNumber > taskManagement.getNumberOfTasks()));
    }

    private String printOutput(Task deletedTask, int size) {
        String outputString = "This task has been deleted:\n";
        outputString += String.format("%s\n", deletedTask);
        outputString += String.format("Total number of tasks: %d\n", size);
        return outputString;
    }

    /**
     * Executes delete command to delete a task.
     * @param taskManagement TaskManagement object where Task to be deleted is stored.
     * @return String output upon successful deletion of Task.
     */
    public String execute(TaskManagement taskManagement) {
        try {
            int taskNumber = getInputNumber(taskNumberString);

            if (isInvalidTaskNumber(taskNumber, taskManagement)) {
                return "Invalid task number. Not stonks!\n";
            }
            Task deletedTask = taskManagement.deleteTask(taskNumber);
            return printOutput(deletedTask, taskManagement.getNumberOfTasks());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Describes usage of delete command.
     * @return String describing the delete command.
     */
    public static String displayHelp() {
        String outputString = "Given a task number, deletes that task from list.\n";
        outputString += "Format of arguments: delete [task number]\n";
        return outputString;
    }
}
