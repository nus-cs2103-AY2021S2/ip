package com.tjtanjin.steve.commands;

import com.tjtanjin.steve.tasks.TaskHandler;

/**
 * Entry point for handling logic and execution of undo command.
 */
public class UndoCommand {

    private final TaskHandler TASK_HANDLER;
    private final String DESCRIPTION;

    /**
     * Constructor for UndoCommand.
     *
     * @param taskHandler provides access for command to modify tasks
     */
    public UndoCommand(String description, TaskHandler taskHandler) {
        this.DESCRIPTION = description;
        this.TASK_HANDLER = taskHandler;
    }

    /**
     * Gets the description of undo command.
     *
     * @return description of undo command
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Forwards the undo operation to TaskHandler.
     *
     * @return string response after operation is done
     */
    public String execute() {
        return TASK_HANDLER.undoTaskAction();
    }
}
