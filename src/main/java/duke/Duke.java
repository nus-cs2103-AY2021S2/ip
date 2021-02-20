package duke;

import java.io.IOException;

import commands.Command;
import commands.ExitCommand;
import data.TaskList;
import parser.Parser;
import parser.ParserException;
import storage.StorageFile;
import ui.TextUi;

public class Duke {
    private TextUi ui;
    private Parser parser;
    private StorageFile storage;
    private TaskList tasks;

    /**
     * Creates a duke instance that reads from
     */
    public Duke() {
        ui = new TextUi();
        parser = new Parser();
        storage = new StorageFile();
    }

    /**
     * Initialises duke with tasks from the savefile
     *
     * @throws IOException
     */
    public void initialise() throws IOException {
        tasks = storage.load();
    }

    /**
     * Returns the greeting message
     *
     * @return greetingMessage
     */
    public String getGreetingMessage() {
        return ui.getGreetingMessage();
    }

    /**
     * Gets the response for a given input
     *
     * @param input
     * @return DukeResponse with message
     * @throws IOException
     */
    public DukeResponse getResponse(String input) throws IOException {
        Command command = null;
        String message;
        try {
            command = parser.parseCommand(input);
            message = command.execute(tasks, ui);
        } catch (ParserException pe) {
            message = pe.getMessage();
        }

        storage.save(tasks);

        DukeResponse response = new DukeResponse(message);

        if (ExitCommand.isExit(command)) {
            response.setExit(true);
        }

        return response;
    }
}
