package dukeproject;

import java.io.FileNotFoundException;

import gui.Ui;
import javafx.scene.control.TextArea;

/**
* The Duke application is a to do list application where users are able to
* create, remove and see their to do list.
*
* @author  Low En Hao
* @version 0.1
* @since   2021-01-26
*/
public class Duke {

    private final Storage storage;
    private TaskList taskList;
    private Ui ui = null;

    /**
     * Returns the duke object with access to the data in the file path.
     * If the data is not empty, load the file content into the task list.
     * Else, load an empty task list where the user can start using the application.
     *
     * @param filePath File path in the hard disk to store the current task list.
     * @throws DukeException for any error.
     */
    public Duke(String filePath) throws DukeException {
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.loadFileContent());
        } catch (FileNotFoundException | DukeException ex) {
            taskList = new TaskList();
        } catch (Exception ex) {
            throw new DukeException();
        }
    }

    public void setUi(TextArea textOutput) {
        ui = new Ui(textOutput);
    }

    /**
     * Runs and understand the user input.
     *
     * @param userInput User input from the GUI's text box.
     */
    public boolean runUserInput(String userInput) {
        return new Parser().parseUserInput(userInput, ui, storage, taskList);
    }
}
