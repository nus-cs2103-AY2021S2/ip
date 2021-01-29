
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke object
     * @param filePath string that shows the filepath to the file that stores past data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main Duke program
     */
    public void run() {
        ui.intro();
        ui.showLine();
        boolean isBye = false;
        while (!isBye) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                ui.showLine();
                c.execute(tasks, ui, storage);
                isBye = c.isBye();
                ui.showLine();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }

    }
    public static void main(String[] args) {
        new Duke("text-ui-test/data.txt").run();
    }

}
