import java.io.IOException;

import java.time.format.DateTimeParseException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        storage.initialize();
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showIOError();
            } catch (StringIndexOutOfBoundsException e) {
                ui.showOutOfBoundsError();
            } catch (DateTimeParseException e) {
                ui.showDateTimeParseError();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("../ip/data/Duke.txt").run();
    }
}