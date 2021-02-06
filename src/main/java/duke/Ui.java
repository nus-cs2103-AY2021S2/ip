package duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.uielement.DialogBox;
/**
 * Handles the interaction the user and the program.
 */
public class Ui extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    private Duke app;

    public Ui(Duke app) {
        this.app = app;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
           handleError(e);
        }
    }

    /**
     * Creates a dialog boxes, one echoing user input and then appends them to
     * the dialog container. It then returns the user input.
     * 
     * @return input of the user.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input));
        userInput.clear();
        app.handleInput(input);
    }

    /**Creates the Duke dialogue box along with the information to be printed
     * to the window.
     * 
     * @param output the string that is to be printed onto the Ui.
     */
    public void createDukeDialog(String output) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(output));
    }

    /**
     * Generates the string representation of the introduction.
     *
     */
    public void handleIntro() {
        String intro = "Hello I'm Duke. What can I do for you?\n";

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(intro));
    }

    /**
     * Creates the Duke dialogue box with the error information displayed.
     * @param e exception that was thrown
     */
    public void handleError(Exception e) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(e.getMessage()));
    }

    /**
     * Closes the Duke app when called.
     */
    public void handleExit() {
        Stage stage = (Stage) userInput.getScene().getWindow();
        stage.close();
    }
}
