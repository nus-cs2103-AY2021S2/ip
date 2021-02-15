package com.tanboonji.duke;

import com.tanboonji.duke.command.Command;
import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.model.AliasMap;
import com.tanboonji.duke.model.TaskList;
import com.tanboonji.duke.parser.CommandParser;
import com.tanboonji.duke.storage.Storage;

/**
 * The Duke class manages the task list and data storage, executes commands and return command responses.
 */
public class Duke {

    private static final String WELCOME_MESSAGE = "Hello! I'm Duke.\n"
            + "What can I do for you today?";
    private static final String EXIT_MESSAGE = "Sorry, command cannot be executed.\n"
            + "I am currently shutting down...";
    private Storage storage;
    private TaskList taskList;
    private AliasMap aliasMap;
    private boolean isShuttingDown = false;

    /**
     * Default class constructor.
     */
    public Duke() {
    }

    /**
     * Initialises Duke by loading task list from storage.
     *
     * @return String response from initialisation.
     */
    public String initialise() {
        storage = new Storage();

        try {
            taskList = storage.loadTask();
            aliasMap = storage.loadAlias();
        } catch (DukeException e) {
            return e.getMessage();
        }

        assert(taskList != null);

        return WELCOME_MESSAGE;
    }

    /**
     * Executes user command and returns response from command.
     *
     * @param input User input command.
     * @return String response from executing user command.
     */
    public String getResponse(String input) {
        if (isShuttingDown) {
            return EXIT_MESSAGE;
        }

        Command command;
        try {
            input = CommandParser.parseAlias(input, aliasMap);
            command = CommandParser.parseCommand(input);
            command.addTaskList(taskList);
            command.addAlias(aliasMap);
            String result = command.execute();

            if (command.shouldSaveData()) {
                saveData();
            }

            if (command.shouldExitDuke()) {
                isShuttingDown = true;
            }

            return result;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Saves task list and alias map data to local disk.
     *
     * @throws DukeException If an error occurs while saving data to local disk.
     */
    public void saveData() throws DukeException {
        storage.saveTask(taskList);
        storage.saveAlias(aliasMap);
    }

    /**
     * Returns boolean variable stating if Duke is shutting down.
     *
     * @return True if Duke is shutting down, else false.
     */
    public boolean isShuttingDown() {
        return isShuttingDown;
    }
}
