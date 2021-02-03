import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Duke class is the main program responsible for instantiating all required classes
 * and methods.
 */
public class Duke extends Application {
    private final Storage ine;
    private final Ui ui;
    private final Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Instantiates required classes.
     */
    public Duke() {
        this.ui = new Ui();
        TaskList tasks = new TaskList(ui);
        this.ine = new Storage(tasks, ui);
        this.parser = new Parser(tasks, ui);
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(ui.welcome(),
                new ImageView(duke)));

        Label importRes;
        try {
            importRes = ine.importData();
        } catch (IOException e) {
            importRes = ui.ioException();
        }
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(importRes,
                new ImageView(duke)));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        String input = userInput.getText();
        Label userText = new Label(input);
        Label dukeText;
        if (input.equals("bye")) {
            dukeText = ui.bye();
        } else {
            dukeText = parser.parse(input);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
        if (input.equals("bye")) {
            Thread t = new Thread(() -> {
                try {
                    ine.exportData();
                    Thread.sleep(1500);
                    Platform.exit();
                } catch (IOException e) {
                    dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(ui.ioException(),
                            new ImageView(duke)));
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            });
            t.start();
        }
    }
}