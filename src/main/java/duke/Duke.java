package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * the agent program to run duke.Duke
 */
public class Duke extends Application {
    private static final String FILE_PATH = "./data/duke.txt";
    private Ui ui;
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/WallE.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Eve.png"));
    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/images/Galaxy.jpg"));

    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(FILE_PATH);

        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printMsg(e.getMessage());
            this.tasks = new TaskList(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.printGreetingMsg();

        boolean isExit = false;
        while(!isExit) {
            String fullCommand = ui.readCommand();
            ui.printLine();
            try {
                Command command = parser.parseCommand(fullCommand);
                command.execute(tasks, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.printMsg(e.getMessage());
            }
            ui.printLine();
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        dialogContainer = new VBox();
        BackgroundImage backgroundimage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
//                new BackgroundSize(385, 535, false, false, false, false));
        Background background = new Background(backgroundimage);
        dialogContainer.setBackground(background);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added here later
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(stage);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(stage);
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(Stage stage) {
        String fullCommand = userInput.getText();

        Label userText = new Label(fullCommand);

        String resp = getResponse(fullCommand);

        Label dukeText = new Label(resp);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();

        if (resp.equals("Bye!")) {
            stage.close();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String fullCommand) {
        try {
            Command command = parser.parseCommand(fullCommand);
            String resp = command.execute(tasks, storage);
            //            isExit = command.isExit();
            return resp;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
