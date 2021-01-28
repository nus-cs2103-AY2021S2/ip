package duke;

import duke.commands.Command;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Main Duke class.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        this(null);
    }

    public Duke(String directoryPath) {
        this.ui = new Ui();
        if (directoryPath != null) {
            this.storage = new Storage(Paths.get(directoryPath));
        } else {
            this.storage = new Storage();
        }
        try {
            this.tasks = storage.readTasks();
        } catch(IOException e) {
            ui.printError("Unable to create file");
            this.tasks = new TaskList();
        } catch(Exception e) {
            ui.printError("Unable to parse file");
            this.tasks = new TaskList();
        }
    }


    /**
     * Executes the Duke program by reading in commands and giving appropriate outputs.
     */
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
