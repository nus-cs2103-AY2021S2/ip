import java.io.IOException;

import duke.*;
import duke.exception.DukeCommandException;
import duke.exception.DukeToDoException;
import duke.command.Command;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke object with default filepath for storage
     */
    public Duke() {
        this("tasks.txt");
    }

    /**
     * Constructor for Duke object with custom filepath for storage
     * @param filePathStr string of the custom filepath for storage
     */
    public Duke(String filePathStr) {
        ui = new Ui();
        storage = new Storage(filePathStr);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.execute(tasks, ui, storage);
        } catch (IOException | DukeCommandException | DukeToDoException e) {
            return ui.showError(e.getMessage());
        }
    }

}
