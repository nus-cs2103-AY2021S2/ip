package tlylt.haha;

import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Haha extends Application {
    private final TaskList database = new TaskList();
    private final Storage storage = new Storage();
    private final Ui ui = new Ui();
    private ScrollPane scrollPane = new ScrollPane();
    private VBox dialogContainer = new VBox();
    private TextField userInput = new TextField();
    private Button sendButton = new Button("Send");
    private Scene scene;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image hahaImage = new Image(this.getClass().getResourceAsStream("/images/DaHaha.png"));

    public Haha() {
        List<String> list = this.storage.getTasks();
        this.database.readTasks(list);
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    @Override
    public void start(Stage stage) {
        // Step 1 Setting up required components
        setupComponents(stage);

        // Step 2 Formatting the window to look as expected
        formatStage(stage);
        formatScrollPane();
        formatSmallerComponents();
        setLayout();

        // Step 3 Add functionality to handle user input
        configureUserInteraction();

        // Starter
        new Haha();
        dialogContainer.getChildren().add(makeHahaDialogBox(ui.welcome()));
        stage.setScene(scene);
        stage.show();
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private void setupComponents(Stage stage) {
        stage.getIcons().add(hahaImage);
        // The container for the content of the chat to scroll
        scrollPane.setContent(dialogContainer);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);
        mainLayout.setBackground(new Background(
                new BackgroundFill(Color.rgb(231, 239, 248), CornerRadii.EMPTY, Insets.EMPTY)));
        this.scene = new Scene(mainLayout);

    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private void formatStage(Stage stage) {
        stage.setTitle("Haha");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private void formatScrollPane() {
        scrollPane.setPrefSize(400, 572);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        dialogContainer.setBackground(new Background(
                new BackgroundFill(Color.rgb(231, 239, 248), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private void setLayout() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private void formatSmallerComponents() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private void configureUserInteraction() {
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());

        // Scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private DialogBox makeUserDialogBox(String text) {
        Label label = new Label(text);
        return DialogBox.getUserDialog(label, new ImageView(userImage));
    }

    private void configureSendButton() {
        sendButton.setPrefWidth(55.0);
        sendButton.setOnMouseClicked((event) -> handleUserInput());
    }
    
    private DialogBox makeHahaDialogBox(String text) {
        Label label = new Label(text);
        return DialogBox.getHahaDialog(label, new ImageView(hahaImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private void handleUserInput() {
        String rawUserInput = userInput.getText();

        dialogContainer.getChildren().addAll(
                makeUserDialogBox(rawUserInput),
                makeHahaDialogBox(getResponse(rawUserInput))
        );

        userInput.clear();
    }


    /**
     * Generates a response from user input.
     *
     * @param input raw input string from user.
     * @return String response from the program after commands are executed.
     */
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

