package duke;

import duke.command.Command;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.Scanner;

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

    /**
     * The driver method to run a created Duke object that responds to user input.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCmd = ui.readCommand();
                Command parsedCmd = Parser.parse(fullCmd);
                parsedCmd.execute(storage, ui, taskList);
                isExit = parsedCmd.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Main method which initializes and runs Duke.
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
