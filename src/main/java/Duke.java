import java.io.IOException;

import duke.*;
import duke.exception.DukeCommandException;
import duke.exception.DukeToDoException;

import duke.command.Command;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for Duke object with default filepath for storage
     */
    public Duke() {
        this("tasks.txt");
    }

    /**
     * Constructor for Duke object with custom filepath for storage
     * @param filePathStr string of the custom filepath for storage
     */
    public Duke(String filePathStr) {
        ui = new Ui();
        storage = new Storage(filePathStr);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.execute(tasks, ui, storage);
        } catch (IOException | DukeCommandException | DukeToDoException e) {
            return ui.showError(e.getMessage());
        }
    }

}
