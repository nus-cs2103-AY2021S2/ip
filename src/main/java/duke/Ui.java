package duke;

import java.io.IOException;

import duke.uielement.DialogBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    /**
     * Constructor method for Ui object.
     * @param app The Duke object the UI is bound to.
     */
    public Ui(Duke app) {
        this.app = app;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            assert fxmlLoader != null : "FXML file not loaded. Location specified might be wrong";
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            handleError(e);
        }
        this.layout();
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles the user input, showing in the Ui.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input));
        userInput.clear();
        app.handleInput(input);
    }

    /**Creates the Duke dialogue box along with the information to be printed to the window.
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
        DialogBox db = DialogBox.getErrorDialog(e.getMessage());
        dialogContainer.getChildren().add(db);
    }

    /**
     * Overloaded method for {@link Ui#handleError(Exception)}.
     * Passes the string representation of the error message.
     * @param e string representation of the error message.
     */
    public void handleError(String e) {
        DialogBox db = DialogBox.getErrorDialog(e);
        dialogContainer.getChildren().add(db);
    }

    /**
     * Closes the Duke app when called.
     */
    public void handleExit() {
        Stage stage = (Stage) userInput.getScene().getWindow();
        stage.close();
    }
}
