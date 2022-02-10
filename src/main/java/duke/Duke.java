package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke is a program for task tracking.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException ex) {
            tasks = new TaskList();
        }
    }

    /**
     * Constructor for Duke.
     *
     * @param filePath a path representing the location of duke save file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException ex) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        String request = input;
        Parser parser = new Parser(request);
        Command command = parser.parse();
        String message = command.execute(tasks, ui, storage);
        return message;
    }

}
