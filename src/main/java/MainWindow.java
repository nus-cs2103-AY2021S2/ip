import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
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
    private Stage stage;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
//        String sage = " ____       __      ________  _______.\n"
//                    + "/  __|     /  \    /   _____||   ____|\n"
//                    + "|  |__    / __ \   |  |  ___ |  |__.  \n"
//                    + "\__   \  / /__\ \  |  | |_  ||   __|  \n"
//                    + " __|  | /  ____  \ |  |___| ||  |____.\n"
//                    + "|_____//__/    \__\\________/|_______|\n";
        String sageFormattedSpacing = "  ____          _        _______     ______.\n"
                + "/    __|       /  \\     /    _____|  |     ___|\n"
                + "|    |__      / __ \\    |   |  ___    |    |__.  \n"
                + "\\__    \\    / /__\\ \\  |   |  |__  |  |     __|  \n"
                + " __|    |  /   ___   \\ |   |___|   |  |    |___.\n"
                + "|_____//__/      \\__\\\\______/  |_______|\n";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(sageFormattedSpacing
                        + "    ________________________________________________________\n"
                        + "     Unique Skill: Sage\n"
                        + "     >Acquired\n"
                        + "    ________________________________________________________\n", dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            stage.close();
        }
    }
}
