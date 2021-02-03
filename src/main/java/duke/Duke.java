package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




public class Duke extends Application {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/User.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/Duwuke.jpg"));

    /**
     * Instantiates a Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("test/duke.txt");

        try {
            taskList = storage.load();
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Starts and runs Duke.
     *
     * @param stage The stage for Duke GUI.
     */
    @Override
    public void start(Stage stage) {
        Parser parser = new Parser();

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
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.greet(), duke));

        sendButton.setOnMouseClicked((event) -> {
            String userText = userInput.getText();
            String dukeText = parser.parse(taskList, userInput.getText());
            storage.saveAsFile(taskList);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, user),
                    DialogBox.getDukeDialog(dukeText, duke)
            );
            if (userText.equals("bye")) {
                stage.close();
            }
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            //handleUserInput();
            String userText = userInput.getText();
            String dukeText = parser.parse(taskList, userInput.getText());
            storage.saveAsFile(taskList);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, user),
                    DialogBox.getDukeDialog(dukeText, duke)
            );
            if (userText.equals("bye")) {
                stage.close();
            }
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));

    }

}
