package com.tanboonji.duke.command;

import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.model.TaskList;

public abstract class Command {

    protected TaskList taskList;

    public Command() {
    }

    public void addTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns boolean value indicating if command causes Duke to exit.
     *
     * @return True if Duke exits, else false.
     */
    public boolean shouldExit() {
        return false;
    }

    /**
     * Returns boolean value indicating if any tasks are updated and requires client to save updated data to file.
     *
     * @return True if tasks are updated, else false.
     */
    public abstract boolean shouldSave();

    /**
     * Executes command.
     *
     * @return String result of output after command is executed.
     * @throws DukeException If any error occurs during command execution.
     */
    public abstract String execute() throws DukeException;
}
