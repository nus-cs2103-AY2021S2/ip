package com.tjtanjin.ip;

/**
 * Entry point for handling logic and execution of done command.
 */
public class DoneCommand {

    private final TaskHandler taskHandler;

    /**
     * Constructor for DoneCommand.
     * @param taskHandler provides access for command to modify tasks
     */
    public DoneCommand(TaskHandler taskHandler) {
        this.taskHandler = taskHandler;
    }

    /**
     * Forwards the mark task as done operation to TaskList.
     * @param index of task to mark as done
     */
    public String execute(int index) {
        return taskHandler.markTaskDone(index);
    }
}
