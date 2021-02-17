package antonio.command;

import antonio.AntonioException;
import antonio.TaskList;
import antonio.task.Task;
/**
 * Represents a command that deletes task from the task list.
 */
public class DeleteCommand implements Command {

    private int deletedID;
    private Task deletedTask;
    private int numTasks;

    /**
     * Constructs a delete command.
     * @param deletedID ID of the task to be deleted.
     */
    public DeleteCommand(int deletedID) {
        this.deletedID = deletedID;
    }

    /**
     * Returns a boolean value to signal the bot to exit.
     * @return True if command signals bot to be terminated.
     */
    public boolean shouldExit() {
        return false;
    }

    /**
     * Gets the reply message.
     * @return The reply message for this command.
     */
    public String getResponse() {
        return "Cosa certa! I've removed this task:\n  " + deletedTask
            + "\nNow you have " + numTasks + " tasks in the list.\nPrego";
    }

    /**
     * Executes the command.
     * @param taskList List of tasks to be used for execution of the command.
     * @return List of tasks after the execution of the command.
     */
    public TaskList execute(TaskList taskList) throws AntonioException {
        if (deletedID > taskList.getSize()) {
            throw new AntonioException("Please enter a valid number that is on the task list");
        }
        deletedTask = taskList.getTask(deletedID);
        taskList.removeTask(deletedID);
        numTasks = taskList.getSize();
        return taskList;
    }

}
