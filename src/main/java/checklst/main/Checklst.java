package checklst.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

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

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

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
            Label dialogLabel = getDialogLabel(userInput.getText());
            dialogLabel.setFont(STANDARD_FONT);
            dialogContainer.getChildren().add(dialogLabel);
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            Label dialogLabel = getDialogLabel(userInput.getText());
            dialogLabel.setFont(STANDARD_FONT);
            dialogContainer.getChildren().add(dialogLabel);
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Main function to run the Checklst Program.
     */
    public void run() {

        TaskList taskList = new TaskList();
        String[] input;

        try {
            String[] pastCommandHistory = Files.readString(Paths.get("./data/checklst.txt")).split("\n");
            for (String command: pastCommandHistory) {
                if (command.equals("")) {
                    continue;
                }
                input = command.split(" ", 2);
                this.parser.parseHistoryCommand(input, taskList);
            }
            this.ui.sendOutput("History successfully restored!");
        } catch (InvalidPathException | IOException e) {
            this.ui.sendOutput("No history found... Initializing from blank state!");
        }

        this.ui.sendWelcome();

        input = ui.readCommand();

        while (!input[0].equals("bye")) {
            this.parser.parse(input, ui, taskList, storage);
            input = ui.readCommand();
        }

        this.ui.sendOutput("Bye! Hope to see you again!");

    }

}
