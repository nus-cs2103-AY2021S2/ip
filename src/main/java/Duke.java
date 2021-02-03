import java.io.IOException;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.exception.DukeCommandException;
import duke.exception.DukeException;
import duke.exception.DukeToDoException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePathStr) {
        ui = new Ui();
        storage = new Storage(filePathStr);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try{
                String fullCommand = ui.getUserCommand();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException | DukeCommandException e) {
                ui.showError(e.getMessage());
            } catch (DukeToDoException e) {
                 ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

}
