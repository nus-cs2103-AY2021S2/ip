package gui;

import dukeproject.Duke;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for duke project.ui.gui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private TextArea textOutput;

    private Duke duke;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        duke.setUi(textOutput);
    }

    @FXML
    private void handleUserInput() {
        // Get user inputs and run the user input command
        String input = userInput.getText();
        boolean canContinue = duke.runUserInput(input);
        userInput.clear();

        // Close the program if the user enters bye
        if (!canContinue) {
            Stage stage = (Stage) sendButton.getScene().getWindow();
            stage.close();
        }
    }

}
