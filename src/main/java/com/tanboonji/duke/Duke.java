package com.tanboonji.duke;

import com.tanboonji.duke.command.Command;
import com.tanboonji.duke.command.HelpCommand;
import com.tanboonji.duke.exception.DukeException;
import com.tanboonji.duke.model.TaskList;
import com.tanboonji.duke.parser.CommandParser;
import com.tanboonji.duke.storage.Storage;

/**
 * The Duke class manages the task list and data storage, executes commands and return command responses.
 */
public class Duke {

    private static final String FILE_DIR = "duke.data";
    private Storage storage;
    private TaskList taskList;

    /**
     * Default class constructor.
     */
    public Duke() {
    }

    /**
     * Initialises Duke by loading task list from storage.
     */
    public void initialise() {
        storage = new Storage(FILE_DIR);

        try {
            taskList = storage.load();
        } catch (DukeException e) {

        }
    }

    /**
     * Executes user command and returns response from command.
     *
     * @param input user command.
     * @return String response from executing user command.
     */
    public String getResponse(String input) {
        Command command;
        try {
            command = CommandParser.parse(input);
            command.addTaskList(taskList);
            String result = command.execute();

            if (command.shouldSave()) {
                storage.save(taskList);
            }

            return result;
        } catch (IllegalArgumentException e) {
            return HelpCommand.ERROR_MESSAGE + HelpCommand.COMMAND_LIST;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
