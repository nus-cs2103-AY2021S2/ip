package duke;

import duke.command.Command;
import duke.command.WelcomeCommand;
import duke.task.TaskList;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a task manager
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor with specified file path.
     * 
     * @param filePath The path to load and save from
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (DateTimeParseException e) {
            ui.showReadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Runs the duke programme.
     */
    public void run() {
        Command c = new WelcomeCommand();
        c.execute(tasks, ui, storage);
        while (!c.isExit()) {
            try {
                String fullCommand = ui.readCommand();
                c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
