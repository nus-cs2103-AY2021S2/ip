package duck;

import duck.operation.CommandGui;
import duck.operation.Gui;
import duck.operation.Parser;
import duck.operation.Storage;
import duck.task.TaskList;
import javafx.application.Application;
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

import java.io.IOException;


public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Gui gui;
    private AnchorPane mainLayout;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * initialize mainLayout
     */
    private void initMainLayout() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
    }

    /**
     * Set the showing scene
     *
     * @param stage  the stage which control the scene
     * @param scene  the showing scene
     * @param title  the title of scene
     * @param height the height of scene
     * @param width  the width of scene
     */
    private void setScene(
            Stage stage, Scene scene, String title, double height, double width) {
        stage.setScene(scene);
        stage.show();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setMinHeight(height);
        stage.setMinWidth(width);
    }

    private void setPanePrefSize() {
        mainLayout.setPrefSize(600.0, 800.0);

        setScrollPane(scrollPane, 585, 735);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(525.0);

        sendButton.setPrefWidth(155.0);
    }

    /**
     * Set scrollPane
     *
     * @param scrollPane
     * @param prefWidth  the width of scroll pane
     * @param prefHeight the height of scroll pane
     */
    private void setScrollPane(
            ScrollPane scrollPane, double prefWidth, double prefHeight) {
        scrollPane.setPrefSize(prefWidth, prefHeight);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    /**
     * set anchor pane
     *
     * @param scrollPane
     * @param sendButton
     * @param userInput
     */
    private void setAnchorPane(
            ScrollPane scrollPane, Button sendButton, TextField userInput) {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws IOException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * function to generate a response to user input.
     *
     * @param input String input text to show
     * @return the specified text
     */
    private String getResponse(String input) throws IOException {
        CommandGui c = Parser.parse(input);
        return c.execute(tasks, gui, storage);
    }

    @Override
    public void start(Stage stage) throws IOException {
        String filePathOfData = ".\\data\\duke.txt";
        storage = new Storage(filePathOfData);
        tasks = new TaskList(storage.load());
        gui = new Gui();

        initMainLayout();

        scene = new Scene(mainLayout);
        setScene(stage, scene, "Duck", 800.0, 600.0);

        setPanePrefSize();

        setAnchorPane(scrollPane, sendButton, userInput);

        Label helloText = new Label(gui.showWelcome());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(helloText, new ImageView(duke))
        );

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
