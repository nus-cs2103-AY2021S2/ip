package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Driver class for Duke project
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Duke class constructor
     *
     * @param filePath Path directory to location of storage file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException ex) {
            ui.display(ex.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts up the Duke Bot program, read and response to user various inputs accordingly
     */
    public void run() {
        boolean continueInput = true;

        while (continueInput) {
            String input = ui.nextCommand();

            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                continueInput = command.continueInput();
            } catch (DukeException ex) {
                ui.display(ex.getMessage());
            }
        }
    }


    /**
     * Main method of Duke Project
     */
    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir") + "/data/Duke.txt";
        new Duke(filePath).run();
    }
}
