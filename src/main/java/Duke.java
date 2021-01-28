import models.Deadline;
import models.Event;
import models.Task;
import models.Todo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.tasks = storage.readTasks();
        } catch(IOException e) {
            ui.printError("Unable to create file");
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.printIntro();

        Scanner stdin = new Scanner(System.in);
        String line = stdin.nextLine();
        boolean end = false;

        while (line != null) {
            ui.printHorizontalLine();

            try {
                Command c = Parser.parse(line);
                c.execute(tasks, ui, storage);
                end = c.isExit();
                storage.writeTasks(tasks);
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            } catch (IOException e) {
                ui.printError("Unable to write to file");
            }

            ui.printHorizontalLine();

            if (end) break;

            line = stdin.nextLine();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
