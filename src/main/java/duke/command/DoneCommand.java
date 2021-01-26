package duke.command;

import duke.TaskList;
/**
 * Represents a command that marks tasks on the task list as done.
 */
public class DoneCommand implements Command {

    private final int id;
    private String response;

    public DoneCommand(int id) {
        this.id = id;
    }

    /** 
     * @return boolean
     */
    public boolean shouldExit() {
        return false;
    }

    /**
     * Gets the reply message.
     * @return The reply message for this command.
     */
    public String getResponse() {
        return "Nice! I've marked this duke.task as done:\n  " + response;
    }

    /**
     * Executes the command.
     * @param taskList List of tasks to be used for execution of the command.
     * @return List of tasks after the execution of the command.
     */
    public TaskList execute(TaskList taskList) {
        taskList.markDone(id);
        response = taskList.getTask(id).toString();
        return taskList;
    }
    
}
