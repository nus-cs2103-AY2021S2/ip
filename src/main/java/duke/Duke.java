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
            ui.showFileNotFound();
            System.exit(1);
        } catch (SaveFileInvalidFormatException e) {
            ui.showInvalidSaveFileFormat();
            System.exit(1);
        } catch (DateTimeParseException e) {
            ui.showInvalidSaveFileDateTimeFormat();
            System.exit(1);
        } catch (IOException e) {
            ui.showFileCreationError();
            System.exit(1);
        } catch (NumberFormatException e) {
            ui.showInvalidSaveFileDurationFormat();
            System.exit(1);
        }
    }

    /** Returns a response message according to the command given.
     *
     * @param fullCommand Command given to Duke bot.
     * @return Message string according to the command given.
     */
    public String getResponse(String fullCommand) {
        try {
            Command command = Parser.parse(fullCommand);
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return ui.getIoErrorString();
        } catch (StringIndexOutOfBoundsException e) {
            return ui.getOutOfBoundsErrorString();
        } catch (DateTimeParseException e) {
            return ui.getDateTimeParseErrorString();
        } catch (NumberFormatException e) {
            return ui.getWrongDurationFormatString();
        }
    }
}
