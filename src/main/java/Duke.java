import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

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

        while (ui.hasNextCommand()) {
            String userCommand = this.ui.getUserCommand();
            this.parser.parse(userCommand);
            try {
                this.storage.save(this.tasks.getTaskList());
            } catch (DukeException e) {
                ui.showSavingError();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
