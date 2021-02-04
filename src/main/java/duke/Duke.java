package duke;

import java.io.IOException;

import duke.command.Command;

/**
 * Duke is a basic to-do list application.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList();
            storage.loadData(taskList);
            ui.showLoadSuccess();
        } catch (IOException e) {
            taskList = new TaskList();
        }
    }

    public Ui getUi() {
        return this.ui;
    }

    /**
     * Parses and subsequently executes the given command input String.
     *
     * @return String output to be displayed
     */
    String getResponse(String input) {
        try {
            Command parsedCmd = Parser.parse(input);
            return parsedCmd.execute(storage, ui, taskList);
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }
}
