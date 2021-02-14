package snom.model;

import snom.logic.commands.Command;
import snom.logic.commands.CommandResponse;
import snom.exceptions.SnomException;
import snom.logic.Parser;
import snom.storage.StorageManager;
import snom.model.task.TaskList;
import snom.ui.Snomio;

/**
 * snom.model.Snom is a Personal Assistant Chatbot that helps
 * a person to keep track of various things.
 *
 * @author Sharptail
 */
public class Snom {
    private StorageManager storage;
    private TaskList taskList;
    private Snomio snomio;

    public Snom(String filePath){
        snomio = new Snomio();
        storage = new StorageManager(filePath);
        try{
            taskList = new TaskList(storage.importTask());
        }catch(SnomException e){
            snomio.showLoadingError();
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
