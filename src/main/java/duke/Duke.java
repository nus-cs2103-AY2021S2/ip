package duke;

import java.io.IOException;

import duke.command.Command;
import duke.component.Parser;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.UnknownCommandException;
import duke.exception.WrongFormatException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Creates a new Duke instance.
     * @param filePath file path
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public String getResponse(String input)
            throws UnknownCommandException, WrongFormatException, EmptyDescriptionException, IOException {
        Command c = Parser.parse(input);
        String msg = c.execute(tasks, ui, storage);
        storage.save(tasks);
        return msg;
    }
}