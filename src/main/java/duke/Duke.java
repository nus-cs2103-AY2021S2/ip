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
            tasks = new TaskList();
            e.printStackTrace();
        }
    }

    public DukeResponse getResponse(String input) {
        Command c;
        try {
            c = Parser.parse(input);
        } catch (UnknownCommandException | EmptyDescriptionException | WrongFormatException e) {
            return new DukeResponse(e.getMessage());
        }
        DukeResponse response = c.execute(tasks, ui, storage);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            return new DukeResponse(e.getMessage());
        }
        return response;
    }
}