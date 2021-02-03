package tlylt.haha;

import java.util.List;

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
import javafx.stage.Stage;

public class Haha extends Application {
    private final TaskList database = new TaskList();
    private final Storage storage = new Storage();
    private final Ui ui = new Ui();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image hahaImage = new Image(this.getClass().getResourceAsStream("/images/DaHaha.png"));

    public Haha() {
        List<String> list = this.storage.getTasks();
        this.database.readTasks(list);
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    @Override
    public void start(Stage stage) {
        // Step 1 Setting up required components

        // The container for the content of the chat to scroll
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Step 2 Formatting the window to look as expected
        stage.setTitle("Haha");
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3 Add functionality to handle user input
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());

        // Scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Starter
        new Haha();
        Label hahaStarter = new Label(ui.welcome());
        dialogContainer.getChildren().add(
                DialogBox.getHahaDialog(hahaStarter, new ImageView(hahaImage))
        );

    }


    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label hahaText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getHahaDialog(hahaText, new ImageView(hahaImage))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private String getResponse(String input) {
        String response = "";
        try {
            LegitCommand command = Parser.parseInput(input);
            if (command.equals(LegitCommand.BYE)) {
                Platform.exit();
                System.exit(0);
            }
            response += database.executeCommand(command, ui);
        } catch (HahaException ex) {
            return ex.toString();
        }
        return response;
    }
}

