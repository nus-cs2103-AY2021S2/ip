package duke;

import javafx.fxml.FXML;

/**
 * Contains the main method to be run.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises the Duke object and loads hard disk data to current taskList.
     *
     * @param filePath the file path that specifies location in hard disk for storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadData());
        } catch (DukeException e) {
            e.getMessage();
        }
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Reads user input and provides the logic for handling each user input.
     *
     * @param duke the initialised Duke object.
     * @param userInput refers to user input from text field.
     */
    @FXML
    public String getResponse(Duke duke, String userInput) {
        assert duke != null;

        try {
            Command command = Parser.parseCommand(userInput);
            String message = command.execute(duke.taskList, duke.ui, duke.storage);
            if (message.equals(duke.ui.showGoodbyeMessage())) {
                System.exit(0);
            }
            return message;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

