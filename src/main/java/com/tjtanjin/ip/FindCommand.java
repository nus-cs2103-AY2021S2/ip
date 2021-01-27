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
     * Forwards the find task operation to TaskList.
     */
    public void execute(String taskName) {
        taskHandler.findTask(taskName);
    }
}
