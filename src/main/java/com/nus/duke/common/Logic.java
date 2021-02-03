package com.nus.duke.common;

import com.nus.duke.command.Command;
import com.nus.duke.data.TaskList;
import com.nus.duke.parser.CommandParser;
import com.nus.duke.storage.TaskListStorage;

public class Logic {

    private TaskListStorage storage;
    private CommandParser parser;
    private TaskList taskList;

    public Logic() {
        this.storage = new TaskListStorage();
        this.parser = new CommandParser();
        try {
            this.taskList = this.storage.load();
        } catch (DukeStorageException e) {
            this.taskList = new TaskList();
        }
    }

    public Command parseInputForCommand(String userInput) {
        return this.parser.parseCommand(userInput);
    }

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
