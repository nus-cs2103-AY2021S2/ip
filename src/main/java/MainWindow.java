import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Region;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * @author WangYihe
 * @author E0424695
 */

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    public AnchorPane mainLayout;

    private Duke duke;

    public Image userImage = new Image(this.getClass().getResourceAsStream("/images/morty.png"));
    public Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/rick.png"));

    @FXML
    public void initialize() {
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        this.scrollPane.setContent(dialogContainer);

        this.userInput = new TextField();
        this.sendButton = new Button("Send");

        this.mainLayout = new AnchorPane();
        this.mainLayout.getChildren().addAll(this.scrollPane, this.userInput, this.sendButton);
    }

    public void initializeComponentProperties() {
        this.mainLayout.setPrefSize(600.0, 600.0);
        this.scrollPane.setPrefSize(385.0, 535.0);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.scrollPane.setVvalue(1.0);
        this.scrollPane.setFitToWidth(true);
        // this.scrollPane.setStyle("-fx-background: #90EE90;");
        this.scrollPane.lookup(".viewport").setStyle(
                "-fx-background-image: url('/images/background/bg.jpg');"
                        + "-fx-background-size: 800 600;"
                        + "-fx-background-position: center;"
        );

        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        this.dialogContainer.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        this.dialogContainer.setSpacing(10.0);

        this.userInput.setPrefWidth(500.0);

        this.sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(this.scrollPane, 1.0);
        AnchorPane.setBottomAnchor(this.sendButton, 1.0);
        AnchorPane.setRightAnchor(this.sendButton, 1.0);
        AnchorPane.setLeftAnchor(this.userInput, 1.0);
        AnchorPane.setBottomAnchor(this.userInput, 1.0);
    }

    public void initializeEventListeners() {
        this.sendButton.setOnMouseClicked((event) -> {
            this.handleUserInput();
        });

        this.userInput.setOnKeyReleased((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.handleUserInput();
            }
        });

        this.dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Set Duke
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Show welcome message
     */
    public void showWelcomeMsg() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.greeting(), dukeImage)
        );
    }

    /**
     * Check whether program is terminating
     */
    public boolean isTerminating() {
        if (userInput.getText().startsWith("bye")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        if (userText.trim().equals("")) {
            return;
        }
        String dukeText = duke.getResponse(userInput.getText());
        if (dukeText.equals("clear\n")) {
            this.dialogContainer.getChildren().clear();
        } else {
            this.dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, userImage),
                    DialogBox.getDukeDialog(dukeText, dukeImage)
            );
        }
        this.userInput.clear();
        if (this.isTerminating()) {
            Platform.exit();
            System.exit(0);
        }
    }

    public void printWelcomeMessage(Duke duke) {
        String welcomeMessage = duke.greeting();
        this.dialogContainer.getChildren().add(DialogBox.getDukeDialog(
                welcomeMessage, dukeImage
        ));
    }
}
