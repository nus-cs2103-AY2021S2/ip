package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Driver class for Duke program
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class
     *
     * @param filePath Path directory to where storage file is located in
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException dukeEx) {
            ui.response(dukeEx.toString());
            tasks = new TaskList();
        }
    }

    /**
     * Initializes the program to start reading commands given by user and responds accordingly
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.fullCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException dukeEx) {
                // Echoes out reason for invalid inputs
                ui.response(dukeEx.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "/data/duke.txt").run();
    }
}
