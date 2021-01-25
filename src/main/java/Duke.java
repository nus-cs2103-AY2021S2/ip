import duke.commands.DukeCommand;
import duke.exceptions.*;
import duke.storage.FileLoader;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Duke {

    private FileLoader loader;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            loader = new FileLoader(filePath);
            tasks = loader.read();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        ui.showWelcomeScreen();
    }

    public void run() {
        boolean isProgramTerminating = false;
        while (!isProgramTerminating) {
            try {
                String input = ui.getUserInput();
                DukeCommand cmd = DukeCommand.parse(input);
                cmd.execute(tasks, ui, loader);
                isProgramTerminating = cmd.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}