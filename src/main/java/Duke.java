import java.io.IOException;

public class Duke {

    private final Ui ui;
    private final Parser parser;
    private Storage storage;
    private TaskList tasklist;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.parser = new Parser();

        try {
            this.storage = new Storage(filePath);
            tasklist = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasklist = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("src\\main\\java\\taskList.txt").run();
    }

    /**
     * Runs the program
     */
    public void run() {
        ui.showLine();
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(tasklist, ui, storage);
                isExit = c.isExit();
            } catch (IOException e) {
                ui.showLoadingError();
            } catch (DukeException | RuntimeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        ui.showExit();
        ui.showLine();
    }
}
