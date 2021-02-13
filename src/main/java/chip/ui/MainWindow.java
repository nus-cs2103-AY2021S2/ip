package chip.ui;

import chip.Chip;
import chip.utils.Messages;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for duke.ui.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Chip chip;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private final Image chipImage = new Image(this.getClass().getResourceAsStream("/images/DaSquirrel.jpg"));

    /**
     * Initialize GUI from FXML.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayChipMessage(Messages.getWelcomeMessage());
    }

    public void setChip(Chip d) {
        chip = d;
    }

    private void displayChipMessage(String response) {
        dialogContainer.getChildren().add(DialogBox.getChipDialog(response, chipImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();

        if (!input.isEmpty()) {
            String response = chip.getResponse(input);
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChipDialog(response, chipImage)
            );

            userInput.clear();
        }
    }
}
