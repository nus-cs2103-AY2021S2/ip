package dbot;

import dbot.command.Command;
import dbot.parser.Parser;
import dbot.exception.DukeException;
import dbot.storage.Storage;
import dbot.tasklist.TaskList;
import dbot.ui.Ui;

public class DBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public DBot(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = storage.load();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInputText = ui.getUserInput();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(userInputText);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new DBot("data/tasks.txt").run();
    }
}
