package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A Personal Assistant Chatbot that helps a person to keep track of various things.
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList taskList;

    /**
     * Constructs Duke chatbot.
     *
     * @param filepath The filepath of where the data is going to be stored at.
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            storage.createFileAndDirectory();
            taskList = new TaskList(storage.load());
        } catch (DukeException ex) {
            ui.showLoadingError();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Runs Duke chatbot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException ex) {
                ui.showError(ex.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
