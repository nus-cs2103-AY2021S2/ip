package duke;


import duke.command.Command;
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
    private Ui ui;

    /**
     * Constructs a new instance of a Duke object.
     * Will attempt to load previously saved tasks from a file.
     * @param pathname The pathname of the file for local data storage.
     */
    public Duke(String pathname) {
        ui = new Ui();
        storage = new Storage(pathname);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            tasks = new TaskList();
        }

    }

    public Duke() {
        this("data/tasks.txt");
    }

    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            String dukeResponse = command.execute(tasks, ui, storage);
            return dukeResponse;
        } catch (DukeException e) {
            return "Oops " + e.getMessage();
        }
    }
}
