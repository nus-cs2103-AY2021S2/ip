package duke;

import duke.exceptions.DukeException;
import duke.command.Command;
import duke.tasks.TaskList;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initialising certain fields.
     *
     * @throws DukeException If there are Exceptions.
     */
    public Duke() throws DukeException {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.displayTasks());
        } catch (DukeException e) {
            tasks = new TaskList();

        }
    }

    /**
     * Returns the response from Duke
     *
     * @param input User input.
     * @return the description of the output.
     * @throws DukeException if there exists an exception from executing the functions.
     */
    public String getResponse(String input) throws DukeException {
        try {
            Command cmd = Parser.parseTask(input);
            return cmd.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Displays the welcome message.
     *
     * @return a String containing the welcome message.
     */
    public String welcomeMessage() {
        return "Hello from Duke!\n" + "\nWhat can I do for you?";
    }
}
