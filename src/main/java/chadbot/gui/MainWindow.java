package chadbot.gui;

import chadbot.Chadbot;
import chadbot.exceptions.InvalidInputException;
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

    private Chadbot chadbot;
    private Image chadRImage = new Image(this.getClass().getResourceAsStream("/images/DaChadR.png"));
    private Image chadLImage = new Image(this.getClass().getResourceAsStream("/images/DaChadL.png"));
    private Image soyjakCry = new Image(this.getClass().getResourceAsStream("/images/DaSoyjakCry.png"));
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
     * @param c The duke object in the Main class.
     */
    public void setDuke(Chadbot c) {
        String greetingMessage = "Welcome to Chadbot.\nWhat can I do for you?";

        chadbot = c;
        chadbot.getStorage().loadData(chadbot.getTaskList());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greetingMessage, chadLImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String errorResponse = InvalidInputException.getExceptionMessage();
        String byeResponse = "Bye :)";
        String input = userInput.getText();
        String response = chadbot.getResponse(input);
        boolean isHelpRequest = input.toLowerCase().contains("help");

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, chadRImage),
                DialogBox.getDukeDialog(response, (isHelpRequest || response.equals(byeResponse))
                        ? soyjakSmirk
                        : response.equals(errorResponse)
                        ? soyjakCry
                        : chadLImage
                )
        );
        userInput.clear();

        if (response.equals(byeResponse)) {
            chadbot.getStorage().saveData(chadbot.getTaskList());
            Platform.runLater(() -> {
                try {
                    Thread.sleep(500);
                    Platform.exit();
                } catch (InterruptedException e) {
                    dialogContainer.getChildren().addAll(
                            DialogBox.getUserDialog(e.getMessage(), chadRImage)
                    );
                }
            });
        }
    }

}
