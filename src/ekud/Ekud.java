package ekud;

import ekud.command.*;
import ekud.common.exception.*;
import ekud.parser.*;
import ekud.storage.*;
import ekud.task.*;
import ekud.ui.*;

public class Ekud {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Ekud(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Ekud("data/tasks.txt").run();
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
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.toString());
            } finally {
                ui.showLine();
            }
        }
    }
}
