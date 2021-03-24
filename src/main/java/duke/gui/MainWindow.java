package duke.gui;

import java.io.IOException;

import duke.Duke;
import duke.Main;
import dukeexception.DukeException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import parser.Parser;
import ui.Ui;

/**
 * Represents the main window component of the GUI.
 *
 * Handles user input and display of Duke's responses on the GUI.
 * Inspired by https://github.com/sc-arecrow/ip.
 */
public class MainWindow extends AnchorPane {
    public static final String LOG_PATH = "./duke.txt";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    /**
     * Instantiates a new MainWindow component.
     */
    public MainWindow() {
        try {
            this.duke = new Duke(LOG_PATH);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dukeWelcome = duke.getUi().printGreeting();
        dialogContainer.getChildren().add(DialogBox.getViscountDialog(dukeWelcome, false));
    }

    /**
     * Initialises the component.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Handles user input and displays Duke's responses accordingly.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = "";
        if (input.equals("bye")) {
            Platform.exit();
        } else {

            boolean isException = false;
            try {
                if (input.contains("/help")) {
                    response = Ui.printHelpMessage();
                } else {
                    response = duke.process(Parser.parse(input));
                }
            } catch (DukeException e) {
                response = "invalid input, please use /help for clarifications";
                isException = true;
            }

            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input),
                    DialogBox.getViscountDialog(response, isException)
            );

            userInput.clear();
        }
    }
}
