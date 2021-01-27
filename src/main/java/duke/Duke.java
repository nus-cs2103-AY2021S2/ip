import command.Command;
import command.ExitCommand;
import component.Parser;
import component.Storage;
import component.TaskList;
import component.Ui;
import exception.DukeException;
import exception.EmptyDescriptionException;
import exception.UnknownCommandException;

import java.io.*;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.save(tasks);
                isExit = (c instanceof ExitCommand) ? true : false;
            } catch (DukeException | UnknownCommandException | EmptyDescriptionException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }


}
