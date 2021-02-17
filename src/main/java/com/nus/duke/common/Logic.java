package com.nus.duke.common;

import com.nus.duke.command.Command;
import com.nus.duke.data.TaskList;
import com.nus.duke.parser.CommandParser;
import com.nus.duke.storage.TaskListStorage;

/**
 * Logic class encapsulates the logic for Duke.
 * <p>
 * Handles high level overview of how inputs are being processed.
 */
public class Logic {

    private final TaskListStorage storage;
    private final CommandParser parser;
    private TaskList taskList;

    /**
     * Default constructor which initializes the storage and parser.
     */
    public Logic() {
        this.storage = new TaskListStorage();
        this.parser = new CommandParser();
        try {
            this.taskList = this.storage.load();
        } catch (DukeStorageException e) {
            this.taskList = new TaskList();
        }
        assert this.taskList != null;
    }

    public Command parseInputForCommand(String userInput) {
        return this.parser.parseCommand(userInput);
    }

    /**
     * Provides the logic for executing a command.
     *
     * @param command command to be executed
     * @return result to be displayed
     */
    public String executeCommand(Command command) {
        command.setContext(this.taskList);
        String result = command.execute();
        try {
            this.storage.save(this.taskList);
        } catch (DukeStorageException e) {
            return e.getLocalizedMessage();
        }
        return result;
    }
}
