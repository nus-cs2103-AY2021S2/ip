package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;

public class Main extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) throws DukeException, Exception {
        //Step 1. Formatting the window to look as expected.

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        Alert instruction = new Alert(Alert.AlertType.INFORMATION);
        instruction.setTitle("Instructions Manual");
        instruction.setContentText(Ui.greet());
        instruction.setHeaderText("These are the commands");
        instruction.show();


        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, dialogContainer);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(800);
        stage.setMinWidth(600.0);

        mainLayout.setPrefSize(600, 800);

        scrollPane.setPrefSize(580, 735);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        Label label = new Label(Ui.greet());
        dialogContainer.getChildren().addAll(label);

        userInput.setPrefWidth(580.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        // more code to be added here later
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (Exception e) {
                Platform.runLater(() -> {
                    dialogContainer.getChildren().addAll(
                            DialogBox.getUserDialog(new Label(userInput.getText()), new ImageView(user)),
                            DialogBox.getDukeDialog(getDialogLabel(e.getMessage()), new ImageView(duke)));
                    userInput.clear();
                });

            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (Exception e) {
                Platform.runLater(() -> {
                    dialogContainer.getChildren().addAll(
                            DialogBox.getUserDialog(new Label(userInput.getText()), new ImageView(user)),
                            DialogBox.getDukeDialog(getDialogLabel(e.getMessage()), new ImageView(duke)));
                    userInput.clear();
                });
            }
        });


    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() throws Exception {
        if (userInput.getText().equals("bye")) {
            Platform.setImplicitExit(true);
            Platform.exit();
        }
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) throws Exception {
        return Parser.read(input);
    }
}
