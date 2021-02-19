package duke;

import java.io.File;
import java.io.IOException;

import duke.command.Command;
import duke.command.CommandResult;
import duke.exceptions.DukeCommandParseException;
import duke.exceptions.DukeDateParseException;
import duke.exceptions.DukeOutOfBoundsException;
import duke.exceptions.DukeStorageException;
import duke.model.TaskList;
import duke.parser.CommandParser;
import duke.storage.Storage;
import duke.ui.MessageGenerator;


/**
 * main class containing the Duke Chatbot main logic.
 */

public class Duke {

    private static final String STORAGE_DIRECTORY_PATH = "data";
    private static final String STORAGE_FILE_PATH = "data/duke.txt";
    private static final String STORAGE_INITIALIZATION_ERROR_MESSAGE = "Cannot Crete file duke.txt";

    private static Storage storage;
    private MessageGenerator messageGenerator;
    private TaskList tasks;
    private boolean isExit = false;


    /**
     * Initializes and loads all the data from storage, as well as create the nexessary classes.
     *
     * @throws IOException when the file does not exist and Duke cannot create the file in the necessary path
     */

    public Duke() throws IOException {
        this.messageGenerator = new MessageGenerator();
        Duke.storage = initializeStorage();
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

    /**
     * Returns the message to display at the start of the program.
     *
     * @return the message to display at the when Duke is started.
     */

    public String startMessage() {
        return messageGenerator.getWelcomeMessage();
    }

    /**
     * Runs the Duke logic given the input string  and gets Duke's response to the input.
     *
     * @param input the string to be passed to Dyke
     * @return the response after Duke analyses the string
     */

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
            return "DATABASE ERROR!" + e.getMessage();
        }
    }

    /**
     * Gets the exit flag for Duke Chatbot
     * @return the flag isExit. It is true if the exit Command has been executed.
     * Else it will be false to show Duke is still running
     *
     */

    public boolean getIsExit() {
        return isExit;
    }

}
