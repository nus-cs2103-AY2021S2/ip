package ekud;

import ekud.command.Command;
import ekud.common.exception.EkudException;
import ekud.parser.Parser;
import ekud.storage.Storage;
import ekud.task.TaskList;
import ekud.ui.Ui;

public class Ekud {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Construct a new instance of the Ekud chatbot.
     *
     * @param filePath Path to the saved tasks
     */
    public Ekud(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (EkudException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    public static void main(String[] args) {
        new Ekud("data/tasks.txt").run();
    }

    /**
     * Main execution loop.
     */
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
            } catch (EkudException e) {
                ui.showError(e.toString());
            } finally {
                ui.showLine();
            }
        }
    }
}
