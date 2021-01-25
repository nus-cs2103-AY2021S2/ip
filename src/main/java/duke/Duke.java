package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static DataStorage storage  = new DataStorage();
    private static UI ui = new UI();
    private static TaskList tasks = new TaskList();

    public static void run() {
        ui.displayWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                tasks.setTaskArraylist(storage.loadFile());
                String fullCommand = ui.readCommand();
                ui.displayLines();
                isExit = Parser.parse(tasks, fullCommand);

            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.displayLines();
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException {

        run();
    }
}
