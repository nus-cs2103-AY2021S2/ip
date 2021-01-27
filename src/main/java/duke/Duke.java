package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.TaskList;
import duke.task.TaskParseException;
import duke.ui.TextUi;
import duke.ui.Ui;

/**
 * A personal task list app.
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private TaskList tasks;

    /**
     * Constructs a Duke instance which uses the given file path as its data file.
     *
     * @param filePath The file path to use for Duke's data file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (StorageException | TaskParseException e) {
            // if the loading fails for any reason, just make a new empty list
            tasks = new TaskList();
        }
        parser = new Parser();
        ui = new TextUi();
    }

    /**
     * Creates a duke instance with a hardcoded data path and runs it.
     *
     * @param args Ignored command line arguments.
     */
    public static void main(String[] args) {
        final String dataFilePath = "data/duke.dat";
        new Duke(dataFilePath).run();
    }

    /**
     * Shows a greeting to the user before entering the app's main loop. Exits when commanded to.
     */
    public void run() {
        ui.showGreeting();

        boolean isExit = false;
        do {
            String input = ui.readCommand();
            Command cmd = parser.parseCmd(input);
            CommandResult cmdResult = cmd.execute(tasks, storage);
            ui.showCommandResult(cmdResult);
            isExit = cmdResult.isExit();
            try {
                storage.save(tasks.serialize());
            } catch (StorageException e) {
                ui.showError("Warning: failed to save tasks!");
            }
        } while (!isExit);

        ui.showFarewell();
    }
}
