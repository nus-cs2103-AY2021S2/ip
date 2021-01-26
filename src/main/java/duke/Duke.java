package duke;

import duke.Exceptions.*;
import duke.ui.Ui;
import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;


public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke() throws DukeException {
        ui = new Ui();
        ui.welcomeMessage();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.displayTasks());
        } catch (DukeException e) {
            ui.printExceptions(e.getMessage());
            tasks = new TaskList();

        }
    }


    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String str = ui.readCommand();
                Command cmd = Parser.parseTask(str);
                cmd.execute(tasks, ui, storage);
                isExit = cmd.isExit();
            } catch (DukeException e) {
                ui.printExceptions(e.getMessage());
            }
        }
    }


    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
