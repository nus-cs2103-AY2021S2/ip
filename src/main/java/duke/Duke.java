package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Driver class for Duke program
 */
public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Duke() {
        initializeDuke(System.getProperty("user.dir") + "/data/duke.txt");
    }

    /**
     * Constructor for Duke class
     *
     * @param filePath Path directory to where storage file is located in
     */
    public void initializeDuke(String filePath) {
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
    public static void run() {
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
        run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
