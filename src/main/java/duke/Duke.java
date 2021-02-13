package duke;


import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;


/**
 * The Duke class serves as the main entry point to the program.
 * Duke is a personal assistant chat bot that helps a person to
 * keep track of various tasks. It uses a CLI to interact with
 * the user and allows users to create, update, delete tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        try {
            storage = new Storage();
            tasks = storage.load();
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        assert storage != null;
        assert tasks != null;

        try {
            Command command = Parser.parseCommand(input);
            String dukeResponse = command.execute(tasks, storage);
            return dukeResponse;
        } catch (DukeException e) {
            return "Oops " + e.getMessage();
        }
    }
}
