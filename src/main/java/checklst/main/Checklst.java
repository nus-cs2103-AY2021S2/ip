package checklst.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

import checklst.exception.ChecklstException;
import checklst.gui.DialogBox;
import checklst.parser.Parser;
import checklst.storage.Storage;
import checklst.task.TaskList;
import checklst.ui.Ui;
import javafx.application.Application;
import javafx.application.Platform;
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

    @Override
    public void start(Stage stage) {

        //Step 1. Formatting the window to look as expected.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        this.userInput = new TextField();
        this.userInput.setFont(STANDARD_FONT);
        this.sendButton = new Button("Send");
        this.sendButton.setFont(STANDARD_FONT);

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

        this.scrollPane.setPrefSize(385, 535);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        this.scrollPane.setVvalue(1.0);
        this.scrollPane.setFitToWidth(true);

        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        this.userInput.setPrefWidth(325.0);

        this.sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.
        this.sendButton.setOnMouseClicked((event) -> {
            this.handleUserInput();
        });

        this.userInput.setOnAction((event) -> {
            this.handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        this.dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Get History
        try {
            String[] history = Files.readString(Paths.get("./data/checklst.txt")).split("\n");
            for (String task: history) {
                this.parser.parseHistory(task, this.taskList);
            }
            this.addDukeMessage("History successfully parsed");
        } catch (InvalidPathException | IOException e) {
            this.addDukeMessage("No history found... Initializing from blank state!");
        } catch (ChecklstException e) {
            this.addDukeMessage(e.getMessage());
        }

        this.addDukeMessage(this.ui.sendWelcome());
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        if (userInput.getText().equals("exit")) {
            try {
                Platform.exit();
            } catch (Exception e) {
            }
        }

        this.addUserMessage(userInput.getText());
        this.addDukeMessage(getResponse(userInput.getText()));
        
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return parser.parse(input.split(" ", 2), this.taskList);
    }

    private void addDukeMessage(String input) {
        this.dialogContainer.getChildren()
            .add(DialogBox.getDukeDialog(new Label(input), new ImageView(duke)));
    }

    private void addUserMessage(String input) {
        this.dialogContainer.getChildren()
            .add(DialogBox.getUserDialog(new Label(input), new ImageView(user)));
    }

    @Override
    public void stop() throws Exception {
        this.storage.saveToFile(this.taskList);
    }

}
