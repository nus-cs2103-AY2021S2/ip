package duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Formats how the send button will look like and its action upon clicking it
     * @param sendButton
     */
    public void buttonSetting(Button sendButton) {
        sendButton.setPrefWidth(55.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

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
    }

    /**
     * Formats how the initial portion of the gui will look like upon running duke
     * @param dialogContainer
     */
    public void vBoxSetting(VBox dialogContainer) {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setPadding(new Insets(10, 5, 10, 5));
        dialogContainer.setSpacing(25);
        Label label = new Label(Ui.greet());
        label.setMinSize(500, 150);
        label.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        Rectangle rect = new Rectangle(500, 150);
        rect.setArcHeight(120.0);
        rect.setArcWidth(120.0);
        label.setClip(rect);
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Verdana Bold", 14));
        dialogContainer.getChildren().addAll(label);
        dialogContainer.setAlignment(Pos.CENTER);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Formats how the the textField of the user input portion will look like
     * @param userInput
     */
    public void textFieldSetting(TextField userInput) {
        userInput.setPrefWidth(580.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
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

    /**
     * Formats how the scroll pane behaves
     * @param scrollPane
     */
    public void scrollPaneSetting(ScrollPane scrollPane) {

        scrollPane.setPrefSize(580, 735);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

    }

    @Override
    public void start(Stage stage) throws DukeException, Exception {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, dialogContainer);

        scene = new Scene(mainLayout);
        Color color = Color.BEIGE;
        scene.setFill(color);

        stage.setScene(scene);
        stage.show();

        buttonSetting(sendButton);
        textFieldSetting(userInput);
        vBoxSetting(dialogContainer);
        scrollPaneSetting(scrollPane);

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(800);
        stage.setMinWidth(600.0);
        mainLayout.setPrefSize(600, 800);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        textToAdd.setFont(new Font("Verdana Bold", 14));
        textToAdd.setTextFill(Color.RED);
        return textToAdd;
    }

    private void handleUserInput() throws Exception {
        if (userInput.getText().equals("bye")) {
            Platform.setImplicitExit(true);
            Platform.exit();
        }
        Label userText = new Label(userInput.getText());
        userText.setFont(new Font("Verdana Bold Italic", 14));
        Label dukeText = new Label(getResponse(userInput.getText()));
        dukeText.setFont(new Font("Verdana", 13));

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
