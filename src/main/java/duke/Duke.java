package duke;

import duke.commands.Command;
import duke.tasks.Storage;
import duke.tasks.TaskList;
import duke.ui.Parser;
import duke.ui.Ui;

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private final TaskList tasks;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.tasks = this.storage.loadTasks();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String input = this.ui.readInput();
            Command c = Parser.parse(input);
            c.execute(this.tasks, this.ui);
            isExit = c.isExit();
        }

        this.storage.saveTasks(this.tasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
