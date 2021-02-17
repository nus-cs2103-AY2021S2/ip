package duke;

import java.io.File;
import java.io.IOException;

import duke.exceptions.DukeDateParseException;
import duke.exceptions.DukeOutOfBoundsException;
import duke.exceptions.DukeCommandParseException;
import duke.exceptions.DukeStorageException;
import duke.model.TaskList;
import duke.parser.CommandParser;
import duke.storage.Storage;
import duke.ui.MessageGenerator;
import duke.command.Command;
import duke.command.CommandResult;

/**
 * main class containing the Duke Chatbot main logic.
 */

public class Duke {

    private final static String STORAGE_DIRECTORY_PATH = "data";
    private final static String STORAGE_FILE_PATH = "data/duke.txt";
    private final static String STORAGE_INITIALIZATION_ERROR_MESSAGE = "Cannot Crete file duke.txt";

    private static Storage storage;
    private TaskList tasks;
    private MessageGenerator messageGenerator;
    private boolean isExit = false;


    Duke() {
        this.messageGenerator = new MessageGenerator();
        try {
            Duke.storage = initializeStorage();
        } catch (IOException e) {
            // cannot create the Storage
            e.printStackTrace();
            throw new RuntimeException(STORAGE_INITIALIZATION_ERROR_MESSAGE);
        }
        try {
            tasks = new TaskList(storage.loadStorage());
        } catch (DukeStorageException | DukeCommandParseException | DukeDateParseException err) {
            // resets the tracking of the number of tasks.
            err.printStackTrace();
            System.out.println(err.getMessage());
            tasks = new TaskList();
        }
    }

    private void createStorageFileIfNotExist() throws IOException {
        File f = new File(STORAGE_FILE_PATH);
        if (!f.exists()) {
            f.createNewFile();
        }
    }

    private void createStorageDirectoryIfNotExist() {
        File directory = new File(STORAGE_DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
    private Storage initializeStorage() throws IOException {
        createStorageDirectoryIfNotExist();
        createStorageFileIfNotExist();
        return new Storage("data/duke.txt");
    }



    public String start(){
        return messageGenerator.getWelcomeMessage();
    }

    public String getResponse(String input) {
        try {
            CommandParser commandParser = new CommandParser(input);
            Command command = commandParser.parseCommand();
            CommandResult commandResult = command.execute(messageGenerator, tasks, storage);
            this.isExit = commandResult.getIsExit();
            return commandResult.getMessageToDisplay();

        } catch (DukeCommandParseException | DukeOutOfBoundsException | DukeDateParseException e) {
            return "OOPS!!! " + e.getMessage();
        } catch (DukeStorageException e) {
            // error saving and loading to database.
            e.printStackTrace();
            throw new  RuntimeException(e.getMessage());
        }
    }

    public boolean getIsExit() {
        return isExit;
    }

}
