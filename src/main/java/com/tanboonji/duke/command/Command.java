package com.tanboonji.duke.command;

import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.model.AliasMap;
import com.tanboonji.duke.model.TaskList;

/**
 * The Command class is an abstract class and its subclasses contain information to execute the respective commands.
 */
public abstract class Command {

    protected TaskList taskList;
    protected AliasMap aliasMap;

    /**
     * Default class constructor.
     */
    public Command() {
    }

    /**
     * Adds task list to command.
     *
     * @param taskList Task list to be added.
     */
    public void addTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds alias map to command.
     *
     * @param aliasMap Alias map to be added.
     */
    public void addAlias(AliasMap aliasMap) {
        this.aliasMap = aliasMap;
    }

    /**
     * Returns boolean value indicating if any tasks are updated and requires client to save updated data to file.
     *
     * @return True if tasks are updated, else false.
     */
    public abstract boolean shouldSaveData();

    /**
     * Returns boolean value indicating if the command causes Duke to exit.
     *
     * @return True if Duke exits, else false.
     */
    public abstract boolean shouldExitDuke();

    /**
     * Executes the command and returns response of command after execution.
     *
     * @return Response of command after execution.
     * @throws DukeException If any error occurs during command execution.
     */
    public abstract String execute() throws DukeException;
}
