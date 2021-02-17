package dukegui;

import dukebody.Duke;
import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;

/**
 * The main GUI window for the duke application
 * which handles the construction of scene settings,
 * nodes and the input / output from the dialog.
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

    // data members
    private Duke duke = new Duke();
    private DialogBox mostRecentDialogBox;
    private boolean hasRecentDukeOutput = false;

    private Image userFace = new Image(this.getClass().getResourceAsStream(
            "/images/DefaultUser.png"));;
    private Image dukeFace = new Image(this.getClass().getResourceAsStream(
            "/images/DogeDuke.png"));
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(
            "eee, dd MMM yyyy HH:mm a");

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Syncs the window with Duke in order to directly
     * trigger a response upon receiving an input as well
     * as making duke sync with the window.
     * @param duke  the duke logic for the application
     */
    public void syncDuke (Duke duke) {
        this.duke = duke;
        duke.syncWindow(this);

        userInput.setOnAction((event) -> {
            if (duke.hasSetupUser()) {
                duke.respondToCommand(pushUserInput());
            } else{
                duke.userSetup(pushUserInput());
            }
        });

        sendButton.setOnMouseClicked((event) -> {
            if (duke.hasSetupUser()) {
                duke.respondToCommand(pushUserInput());
            } else{
                duke.userSetup(pushUserInput());
            }
        });
    }

    // accessors

    /**
     * Accesses the window's current datetime format setting
     * @return  current datetime format being used in dialog text.
     */
    public DateTimeFormatter getDateFormat () {
        return dateFormat;
    }

    // mutators

    /**
     * Pushes the user input from the text box to a dialog box in
     * the dialog container and clears the text box.
     * @return      the user input to output into the dialog.
     */
    @FXML
    private String pushUserInput () {
        String input = userInput.getText();
        dialogContainer.getChildren().add(new DialogBox(input, userFace));
        userInput.clear();
        hasRecentDukeOutput = false;
        return input;
    }

    /**
     * Pushes a message to a dialog box in the dialog container as an
     * output from duke.
     * @param message  the message to output into the dialog.
     */
    @FXML
    public void dukeOutput (String message) {
        if (hasRecentDukeOutput) {
            mostRecentDialogBox.appendText(message);
        } else {
            mostRecentDialogBox = new DialogBox(message, dukeFace, true);
            dialogContainer.getChildren().add(mostRecentDialogBox);
        }

        hasRecentDukeOutput  = true;
    }

    /**
     * Changes the datetime format of the window dialog contents
     * @param formatExpression  an expression that represents the datetime format.
     */
    public void changeDateFormat (String formatExpression) {
        dateFormat = DateTimeFormatter.ofPattern(formatExpression);
    }

    public void exit () {
        Platform.exit();
        System.exit(0);
    }
}
