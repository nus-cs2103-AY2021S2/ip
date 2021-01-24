package duke;

import duke.commands.*;

import duke.parser.Parser;

import duke.exceptions.*;

import duke.storage.Storage;

import duke.tasks.TaskList;

import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.format.DateTimeParseException;

/**
 * This class processes command line inputs and edit list of task.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke with specified save file location.
     *
     * @param filePath File path of the save file.
     */
    public Duke(String filePath) {
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            storage.initialize();
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Error: File does not exists.");
            System.exit(1);
        } catch (InvalidSaveFileFormatException e) {
            System.out.println("Error: Invalid content format in save file");
            System.exit(1);
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date time format in save file or no date time stated");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error happened while trying to create save file");
            System.exit(1);
        }
    }

    /**
     * Start the program of Duke that edits a lists of task.
     */
    public void run() {
        ui.showWelcome();
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
        String dir = System.getProperty("user.dir");
        new Duke(dir + "/data/Duke.txt").run();
    }
}