package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.StorageException;
import duke.task.TaskList;
import duke.task.TaskParseException;
import duke.ui.Gui;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * A personal task list app.
 *
 * @author Benedict Khoo
 */
public class Duke extends Application {
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private TaskList tasks;

    /**
     * Called by JavaFX to initialize the app.
     *
     * @param stage The stage used.
     */
    @Override
    public void start(Stage stage) {
        final String dataFilePath = "data/duke.dat";
        storage = new Storage(dataFilePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (StorageException | TaskParseException e) {
            // if the loading fails for any reason, just make a new empty list
            tasks = new TaskList();
        }
        parser = new Parser();

        Gui gui = new Gui(this::handleInput);
        gui.start(stage);

        ui = gui;
        ui.showGreeting();
    }

    /**
     * Called by JavaFX when the app is closed.
     *
     * @throws Exception If something goes wrong.
     */
    @Override
    public void stop() throws Exception {
        super.stop();
    }

    private void handleInput(String input) {
        Command cmd = parser.parseCmd(input);
        assert cmd != null : "null command!";

        CommandResult cmdResult = cmd.execute(tasks, storage);
        assert cmdResult != null : "null command result!";

        ui.showCommandResult(cmdResult);
        try {
            storage.save(tasks.serialize());
        } catch (StorageException e) {
            ui.showError("Warning: failed to save tasks!");
        }

        if (cmdResult.isExit()) {
            Platform.exit();
        }
    }
}
