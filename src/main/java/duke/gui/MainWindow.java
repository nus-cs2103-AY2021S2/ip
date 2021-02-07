package duke.gui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
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

    private Duke duke;
    private Image chadRImage = new Image(this.getClass().getResourceAsStream("/images/DaChadR.png"));
    private Image chadLImage = new Image(this.getClass().getResourceAsStream("/images/DaChadL.png"));
    private Image soyjakCry = new Image(this.getClass().getResourceAsStream("/images/DaSoyjakCry.png"));

    // this will be used in the near future
    private Image soyjakSmirk = new Image(this.getClass().getResourceAsStream("/images/DaSoyjakSmirk.png"));

    /**
     * Initializes the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the duke object in the Main class to be the duke object in the GUI.
     *
     * @param d The duke object in the Main class.
     */
    public void setDuke(Duke d) {
        duke = d;
        duke.getStorage().loadData(duke.getTaskList());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Hello! What can I do for you?", chadLImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, chadRImage),
                DialogBox.getDukeDialog(response, response.equals("I'm sorry, but I don't know what that means.")
                        ? soyjakCry
                        : chadLImage)
        );
        userInput.clear();

        if (response.equals("")) {
            duke.getStorage().saveData(duke.getTaskList());
            try {
                Thread.sleep(500);
                Platform.exit();
            } catch (InterruptedException e) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(e.getMessage(), chadRImage)
                );
            }
        }
    }

}
