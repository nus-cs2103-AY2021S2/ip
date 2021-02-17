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
    private static final double PREF_WIDTH = 400.0;
    private static final double PREF_HEIGHT = 600.0;
    private static final int SCROLL_PREF_HEIGHT = 572;
    private static final double USER_INPUT_PREF_WIDTH = 325.0;
    private static final double V_VALUE = 1.0;
    private static final double SEND_BUTTON_PREF_WIDTH = 55.0;
    private static final double ANCHOR = 1.0;
    private static final String FX_BACKGROUND_IMAGE_URL_IMAGES_BACKGROUND_JPG =
            "-fx-background-image: url(\"/images/background.jpg\");";
    private final TaskList database = new TaskList();
    private final Ui ui = new Ui();
    private final ScrollPane scrollPane = new ScrollPane();
    private final VBox dialogContainer = new VBox();
    private final TextField userInput = new TextField();
    private final Button sendButton = new Button("Send");
    private Scene scene;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image hahaImage = new Image(this.getClass().getResourceAsStream("/images/DaHaha.png"));


    /**
     * Starts the internal program logic.
     */
    public Haha() {
        Storage storage = new Storage();
        List<String> list = storage.getTasks();
        this.database.readTasks(list);
    }

    /**
     * Starts the Application logic.
     */
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

        // Start internal logic
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
        mainLayout.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
        this.scene = new Scene(mainLayout);

    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private void formatStage(Stage stage) {
        stage.setTitle("Haha");
        stage.setResizable(false);
        stage.setMinHeight(PREF_HEIGHT);
        stage.setMinWidth(PREF_WIDTH);
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private void formatScrollPane() {
        scrollPane.setPrefSize(PREF_WIDTH, SCROLL_PREF_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(V_VALUE);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        dialogContainer.setStyle(FX_BACKGROUND_IMAGE_URL_IMAGES_BACKGROUND_JPG);

    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private void setLayout() {
        AnchorPane.setTopAnchor(scrollPane, ANCHOR);
        AnchorPane.setBottomAnchor(sendButton, ANCHOR);
        AnchorPane.setRightAnchor(sendButton, ANCHOR);
        AnchorPane.setLeftAnchor(userInput, ANCHOR);
        AnchorPane.setBottomAnchor(userInput, ANCHOR);
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private void formatSmallerComponents() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(USER_INPUT_PREF_WIDTH);
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private void configureUserInteraction() {
        configureSendButton();
        userInput.setOnAction((event) -> handleUserInput());

        // Scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(V_VALUE));
    }

    private DialogBox makeUserDialogBox(String text) {
        Label label = new Label(text);
        return DialogBox.getUserDialog(label, new ImageView(userImage));
    }

    private void configureSendButton() {
        sendButton.setPrefWidth(SEND_BUTTON_PREF_WIDTH);
        sendButton.setOnMouseClicked((event) -> handleUserInput());
    }
    private DialogBox makeHahaDialogBox(String text) {
        Label label = new Label(text);
        return DialogBox.getHahaDialog(label, new ImageView(hahaImage));
    }

    /**
     * Creates two dialog boxes and clears the user input after processing.
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

