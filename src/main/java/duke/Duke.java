package duke;

import duke.command.Command;
import duke.command.Parser;

/**
 * Encapsulates Duke, the chatbot application.
 *
 * @author Aaron Saw Min Sern
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Sole constructor for class Duke.
     *
     * @param filePath the file path location at which Duke stores data
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loaded = storage.loadFile();
        if (loaded == null) {
            tasks = new TaskList();
            storage.createDirectoryAndFile();
        } else {
            tasks = loaded;
        }
    }

    /**
     * Returns a response from Duke by entering an user input command.
     *
     * @param   input   input command by the user
     * @return          appropriate response to the input command
     */
    public String getResponse(String input) {
        if (input.equals("")) {
            return "\t...";
        } else {
            try {
                final Command command = Parser.parseCommand(input);
                return command.executeAndGetResponse(tasks, ui, storage);
            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }

}
