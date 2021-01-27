package com.tjtanjin.ip;

/**
 * Entry point for handling logic and execution of find command.
 */
public class FindCommand {

    private final TaskHandler taskHandler;

    /**
     * Constructor for FindCommand.
     * @param taskHandler provides access for command to modify tasks
     */
    public FindCommand(TaskHandler taskHandler) {
        this.taskHandler = taskHandler;
    }

    /**
     * Forwards the find task operation to TaskHandler.
     * @param taskName name of task
     * @return string response after operation is done
     */
    public String execute(String taskName) {
        return taskHandler.findTask(taskName);
    }
}
