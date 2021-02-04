package checklst.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

import checklst.gui.DialogBox;
import checklst.parser.Parser;
import checklst.storage.Storage;
import checklst.task.TaskList;
import checklst.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The Checklst Class represents the entry point of the Checklst Program.
 */
public class Checklst extends Application {

    private static final Font STANDARD_FONT = new Font("Arial", 14);

    private final Ui ui = new Ui();
    private final Parser parser = new Parser();
    private final Storage storage = new Storage();
    private final TaskList taskList = new TaskList();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * The main function of the Checklst Program.
     * Creates and initializes an instance of Checklst and runs it.
     * @param args CLI Arguments.
     */
    public static void main(String[] args) {
        Checklst checklst = new Checklst();
        checklst.run();
    }

    @Override
    public void start(Stage stage) {

        //Step 1. Formatting the window to look as expected.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        userInput.setFont(STANDARD_FONT);
        sendButton = new Button("Send");
        sendButton.setFont(STANDARD_FONT);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Checklst");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(new Label(ui.sendWelcome()), new ImageView(duke)));

    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return parser.parse(input.split(" ", 2), this.ui, this.taskList, this.storage);
    }

    /**
     * Main function to run the Checklst Program.
     */
    public void run() {

        String[] input;

        try {
            String[] pastCommandHistory = Files.readString(Paths.get("./data/checklst.txt")).split("\n");
            for (String command: pastCommandHistory) {
                if (command.equals("")) {
                    continue;
                }
                input = command.split(" ", 2);
                this.parser.parseHistoryCommand(input, this.taskList);
            }
            this.ui.sendOutput("History successfully restored!");
        } catch (InvalidPathException | IOException e) {
            this.ui.sendOutput("No history found... Initializing from blank state!");
        }

        this.ui.sendWelcome();

        input = ui.readCommand();

        while (!input[0].equals("bye")) {
            this.parser.parse(input, ui, this.taskList, storage);
            input = ui.readCommand();
        }

        this.ui.sendOutput("Bye! Hope to see you again!");

    }

}
