import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
    public void run()  {
        ui.intro();
        ui.showLine();
        boolean isBye = false;
        while (!isBye) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
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
