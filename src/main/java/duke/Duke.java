package duke;

import java.io.IOException;

import duke.command.Command;
import duke.util.Tuple;

/**
 * Duke is a basic to-do list application.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs a Duke object with the given filePath as the file to load the task list from.
     * @param filePath path to the of the task list.
     */
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
     * @return Tuple containing output to be displayed and a boolean representing if it is an exit command.
     */
    Tuple<String, Boolean> getResponse(String input) {
        try {
            Command parsedCmd = Parser.parse(input);
            return new Tuple<>(parsedCmd.execute(storage, ui, taskList), parsedCmd.isExit());
        } catch (DukeException e) {
            return new Tuple<>(ui.toErrorString(e), false);
        }
    }
}
