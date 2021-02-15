package snom.model;

import snom.common.exceptions.SnomException;
import snom.logic.Parser;
import snom.logic.commands.Command;
import snom.logic.commands.CommandResponse;
import snom.model.task.TaskList;
import snom.storage.StorageManager;
import snom.ui.Snomio;

/**
 * Snom is a Personal Assistant Chatbot that helps
 * a person to keep track of various things.
 */
public class Snom {
    private StorageManager storage;
    private TaskList taskList;
    private Snomio snomio;

    /**
     * Constructs a {@code Snom}
     *
     * @param filePath file path to store task list
     */
    public Snom(String filePath) {
        snomio = new Snomio();
        storage = new StorageManager(filePath);
        try {
            taskList = storage.importTask();
        } catch (SnomException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns {@code CommandResponse} with the response message and whether to exit after command
     *
     * @param userInput String of user input
     * @return          CommandResponse
     */
    public CommandResponse getResponse(String userInput) {
        try {
            Command command = Parser.parse(userInput);
            CommandResponse response = command.execute(taskList, snomio, storage);
            return response;
        } catch (SnomException e) {
            return new CommandResponse(e.getMessage(), false);
        }
    }

}
