package vergil.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import vergil.components.Ui;
import vergil.components.Storage;
import vergil.components.Parser;
import vergil.components.TaskList;
import vergil.components.ui.DialogBox;

import vergil.types.exceptions.VergilException;

public class Vergil extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("images/user.png"));
    private Image vergil = new Image(this.getClass().getResourceAsStream("images/vergil.png"));

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    /**
     * Constructs a Vergil object with a specified save file path.
     */
    public Vergil() {
        ui = new Ui();
        storage = new Storage("data/vergil_list.sav");
        parser = new Parser();

        try {
            taskList = new TaskList(storage.load());
        } catch (VergilException e) {
            System.out.println(e.getMessage());
            taskList = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setTitle("Vergil");
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

        stage.setScene(scene);
        stage.show();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getVergilDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    public String getResponse(String command) {
        try {
            return parser.parse(command).execute(ui, taskList, storage);
        } catch (VergilException e) {
            return e.getMessage();
        }
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label vergilText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                getUserDialog(userText, new ImageView(user)),
                getVergilDialog(vergilText, new ImageView(vergil))
        );
        userInput.clear();
    }

    public static void main(String[] args) {
        // Do nothing.
    }
}
