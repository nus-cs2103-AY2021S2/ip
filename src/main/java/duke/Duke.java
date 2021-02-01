package duke;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    String imageUrl =
            "https://static.wikia.nocookie.net/moomin/images/0/05/My1.png/revision/latest/top-crop/width/300/height/300?cb=20190914020308";
    String moominUrl =
            "https://i.pinimg.com/originals/19/3a/15/193a1552cc00589da96c9c8ce8cc4ba9.png";
    private Image user = new Image(imageUrl, 160, 60, false, true);
    private Image duke = new Image(moominUrl, 160, 60, false, true);

//    private Image user = new Image(this.getClass().getResourceAsStream("resources/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("resources/images/DaDuke.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Sets up the required objects.
     */
    public Duke() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            storage.loadTasksFromFile(tasks);
        } catch (DukeException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Handles the inputs entered by the user,
     * until the user enters the exit command.
     */
    public void handleUserInputs() {
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.nextUserInput();
                Command command = Parser.parse(userInput);
                command.execute(tasks, ui, storage);
                isRunning = !command.isExitCommand();
            } catch (DukeException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.printGreeting();
        handleUserInputs();
        ui.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
