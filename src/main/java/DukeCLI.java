import java.io.IOException;
import duke.exception.DukeCommandException;
import duke.exception.DukeToDoException;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

public class DukeCLI {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public DukeCLI(String filePathStr) {
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
            } catch (IOException | DukeCommandException | DukeToDoException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    // public static void main(String[] args) {
    //     new DukeCLI("tasks.txt").run();
    // }

}
