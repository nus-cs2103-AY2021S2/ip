package antonio.command;

import antonio.AntonioException;
import antonio.TaskList;
/**
 * Represents a command that marks tasks on the task list as done.
 */
public class DoneCommand implements Command {

    private final int id;
    private String response;

    /**
     * Constructs a done command.
     * @param id ID of task to be marked as done.
     */
    public DoneCommand(int id) {
        this.id = id;
    }

    /**
     * Returns positive if command terminates chat bot.
     * @return True if this command terminates chat bot.
     */
    public boolean shouldExit() {
        return false;
    }

    /**
     * Gets the reply message.
     * @return The reply message for this command.
     */
    public String getResponse() {
        return "Completato! I've marked this task as done:\n  " + response;
    }

    /**
     * Executes the command.
     * @param taskList List of tasks to be used for execution of the command.
     * @return List of tasks after the execution of the command.
     */
    public TaskList execute(TaskList taskList) throws AntonioException {
        if (id > taskList.getSize()) {
            throw new AntonioException("Please enter a valid number that is on the task list");
        }
        taskList.markDone(id);
        response = taskList.getTask(id).toString();
        return taskList;
    }
}
