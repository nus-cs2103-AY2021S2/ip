package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.SaveFileInvalidFormatException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;



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
            //TODO change all the output to be manged by ui.
            System.out.println("Error: File does not exists.");
            System.exit(1);
        } catch (SaveFileInvalidFormatException e) {
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
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showIoError();
            } catch (StringIndexOutOfBoundsException e) {
                ui.showOutOfBoundsError();
            } catch (DateTimeParseException e) {
                ui.showDateTimeParseError();
            }
        }
    }

    /**
     * Main method to start Duke program.
     *
     * @param args
     */
    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        new Duke(dir + "/data/Duke.txt").run();
    }
}
