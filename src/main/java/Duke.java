import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showGreetings();
        Parser parser = new Parser();
        boolean toContinue = true;

        while (toContinue) {
            String userInput = ui.listenToCommand();
            toContinue = ui.respondToCommand(userInput, tasks, storage);
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("data/tasks.txt").run();
    }
}
