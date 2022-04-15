package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Duke extends Application {

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Parser parser;
    private Stage stage;
    private Button sendButton;
    private final Image user = new Image(this.getClass().getResourceAsStream("/User.jpg"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/Duwuke.jpg"));
    private final Image dukeAngryImage = new Image(this.getClass().getResourceAsStream("/DuwukeAngry.jpg"));

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

    /**
     * Performs actions as required based on the current user input, then clears it.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = parser.parse(taskList, userInput.getText());
        storage.saveAsFile(taskList);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke, dukeAngryImage)
        );
        if (userText.equals("bye")) {
            stage.close();
        }
        userInput.clear();
    }

    /**
     * Sets the correct GUI values for the Stage.
     *
     * @param scene The current scene.
     */
    private void setStage(Scene scene) {
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duwuke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    /**
     * Sets the correct GUI values for the MainLayout.
     *
     * @param mainLayout The current MainLayout.
     */
    private void setMainLayout(AnchorPane mainLayout) {
        mainLayout.setPrefSize(400.0, 600.0);
    }

    /**
     * Sets the correct GUI values for the ScrollPane.
     */
    private void setScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    /**
     * Sets the correct GUI values for any DialogContainer.
     */
    private void setDialogContainer() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    /**
     * Sets the correct GUI values for the input bar.
     */
    private void setInputBar() {
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
    }

    /**
     * Sets the correct GUI values for the AnchorPane.
     */
    private void setAnchorPane() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Generates the GUI and its various components for Duwuke.
     */
    public void generateGui() {
        parser = new Parser();

        scrollPane = new ScrollPane();
        userInput = new TextField();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        Scene scene = new Scene(mainLayout);

        setStage(scene);
        setMainLayout(mainLayout);
        setScrollPane();
        setDialogContainer();
        setInputBar();
        setAnchorPane();

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ui.greet(), duke, dukeAngryImage));
    }

    /**
     * Creates the GUI and waits for input.
     */
    public void showGui() {
        generateGui();
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
    }

    /**
     * Starts and runs Duke.
     *
     * @param stage The stage for Duke GUI.
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        showGui();
    }

}
