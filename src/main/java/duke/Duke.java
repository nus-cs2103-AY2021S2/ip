package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser(ui, storage, tasks);
    }

    public void run() {
        this.ui.displayWelcomeMessage();

        while (true) {
            String userCommand = this.ui.getUserCommand();
            boolean exit = this.parser.parse(userCommand);
            try {
                this.storage.save(this.tasks.getTaskList());
            } catch (DukeException e) {
                ui.showSavingError();
            }
            if (exit) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
