package checklst.main;

import checklst.exception.ChecklstException;
import checklst.gui.ChecklstDialog;
import checklst.gui.DialogBox;
import checklst.gui.ErrorDialog;
import checklst.gui.UserDialog;
import checklst.parser.Parser;
import checklst.storage.Storage;
import checklst.task.TaskList;
import checklst.ui.Ui;
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
    private final TaskList taskList = new TaskList();
    private final Parser parser = new Parser(this.taskList);
    private final Storage storage = new Storage();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userIcon = new Image(this.getClass().getResourceAsStream("/images/UserIcon.png"));
    private Image checklstIcon = new Image(this.getClass().getResourceAsStream("/images/ChecklstIcon.png"));

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
        this.scrollPane.setStyle("-fx-background-color: transparent; -fx-background: rgb(207, 232, 243);");

        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        this.userInput.setPrefWidth(325.0);
        this.userInput.setPrefHeight(32.0);
        this.sendButton.setPrefWidth(70.0);
        this.sendButton.setPrefHeight(32.0);

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
            this.storage.parseHistory(this.taskList);
            this.addChecklstMessage("History successfully parsed!");
        } catch (ChecklstException e) {
            this.addErrorMessage(e.getMessage());
        }

        this.addChecklstMessage(this.ui.sendWelcome());

    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        if (userInput.getText().equals("bye")) {
            try {
                Platform.exit();
            } catch (Exception e) {
                // Do nothing
            }
        }

        this.addUserMessage(userInput.getText());

        String response = "";
        try {
            response = this.parser.parse(userInput.getText().split(" ", 2));
            this.addChecklstMessage(response);
        } catch (ChecklstException e) {
            this.addErrorMessage(e.getMessage());
        }

        userInput.clear();
    }

    private void addChecklstMessage(String input) {
        DialogBox newDialogBox = new ChecklstDialog(new Label(input), new ImageView(checklstIcon));
        this.dialogContainer.getChildren().add(newDialogBox);
        VBox.setMargin(newDialogBox, new Insets(3));
    }

    private void addUserMessage(String input) {
        DialogBox newDialogBox = new UserDialog(new Label(input), new ImageView(userIcon));
        this.dialogContainer.getChildren().add(newDialogBox);
        VBox.setMargin(newDialogBox, new Insets(3));
    }

    private void addErrorMessage(String input) {
        DialogBox newDialogBox = new ErrorDialog(new Label(input), new ImageView(checklstIcon));
        this.dialogContainer.getChildren().add(newDialogBox);
        VBox.setMargin(newDialogBox, new Insets(3));
    }

    @Override
    public void stop() throws Exception {
        this.storage.saveToFile(this.taskList);
    }

}
