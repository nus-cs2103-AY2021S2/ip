package com.tanboonji.jhin;

import com.tanboonji.jhin.command.Command;
import com.tanboonji.jhin.exception.JhinException;
import com.tanboonji.jhin.model.AliasMap;
import com.tanboonji.jhin.model.TaskList;
import com.tanboonji.jhin.parser.CommandParser;
import com.tanboonji.jhin.storage.Storage;

/**
 * The Jhin class manages the task list and data storage, executes commands and return command responses.
 */
public class Jhin {

    private static final String WELCOME_MESSAGE = "Hello! I'm Jhin.\n"
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
    public Jhin() {
    }

    /**
     * Initialises Jhin by loading task list from storage.
     *
     * @return String response from initialisation.
     */
    public String initialise() {
        storage = new Storage();

        try {
            taskList = storage.loadTask();
            aliasMap = storage.loadAlias();
        } catch (JhinException e) {
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
    public String getResponse(String input) throws JhinException {
        if (isShuttingDown) {
            return EXIT_MESSAGE;
        }

        input = CommandParser.parseAlias(input, aliasMap);
        Command command = CommandParser.parseCommand(input);
        command.addTaskList(taskList);
        command.addAlias(aliasMap);
        String result = command.execute();

        if (command.shouldSaveData()) {
            saveData();
        }
        if (command.shouldExitJhin()) {
            isShuttingDown = true;
        }

        return result;
    }

    /**
     * Saves task list and alias map data to local disk.
     *
     * @throws JhinException If an error occurs while saving data to local disk.
     */
    public void saveData() throws JhinException {
        storage.saveTask(taskList);
        storage.saveAlias(aliasMap);
    }

    /**
     * Returns boolean variable stating if Jhin is shutting down.
     *
     * @return True if Jhin is shutting down, else false.
     */
    public boolean isShuttingDown() {
        return isShuttingDown;
    }
}
