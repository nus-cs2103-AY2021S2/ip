package duke;

import java.io.IOException;

import duke.command.Command;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;



/**
 * Duke is a basic to-do list application.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList();
            storage.loadData(taskList);
            ui.showLoadSuccess();
        } catch (IOException e) {
            taskList = new TaskList();
        }
    }

    /**
     * The driver method to run a created Duke object that responds to user input.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCmd = ui.readCommand();
                Command parsedCmd = Parser.parse(fullCmd);
                parsedCmd.execute(storage, ui, taskList);
                isExit = parsedCmd.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    String getResponse(String input) {
        try {
            Command parsedCmd = Parser.parse(input);
            return parsedCmd.execute(storage, ui, taskList);
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

    /*
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }
    */


    /**
     * Main method which initializes and runs Duke.
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
