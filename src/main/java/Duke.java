import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (DateTimeParseException e) {
            ui.showReadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Command c = new WelcomeCommand();
        c.execute(tasks, ui, storage);
        while (!c.isExit()) {
            try {
                String fullCommand = ui.readCommand();
                c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.printMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
