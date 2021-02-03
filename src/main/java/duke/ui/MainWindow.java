package duke.ui;

import java.io.IOException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.duke.Duke;
import duke.duke.Main;
import duke.exceptions.InvalidArgumentException;
import duke.exceptions.InvalidCommandException;
import duke.parser.Parser;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * The Main Window. Provides the basic application layout.
 */
public class MainWindow {
    private final ScrollPane scrollPane;
    private final VBox dialogContainer;
    private final TextField userInput;
    private final Button sendButton;
    private final Scene scene;
    private final AnchorPane mainLayout;
    private final Image user;
    private final Image duke;

    /**
     * Creates a {@code MainWindow} object with attributes that constitutes the basic layout of application.
     */
    public MainWindow() {
        this.user = new Image(this.getClass().getResourceAsStream("/images/heartPiglet.png"));
        this.duke = new Image(this.getClass().getResourceAsStream("/images/sparklePooh.png"));
        this.mainLayout = new AnchorPane();
        this.userInput = new TextField();
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        this.sendButton = new Button("Send");
        this.scene = new Scene(mainLayout, Color.LIGHTSTEELBLUE);
    }

    /**
     * Initialises key components of the user interface.
     * @param bot A duke object.
     */
    public void initialise(Duke bot) {
        setMainLayout();
        setUserInput(bot);
        setScrollPane();
        setSendButton(bot);
        setBackgroundColor();
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        Label welcomeMessage = new Label(getResponse(Ui.showWelcomeMessage(bot)));
        welcomeMessage.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        Label line = new Label("\u2500".repeat(50));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcomeMessage, new ImageView(duke)), line);
    }

    /**
     * Sets background colour of user interface.
     */
    public void setBackgroundColor() {
        dialogContainer.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE,
                CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.setStyle("-fx-background: #B0C4DE; -fx-border-color: #B0C4DE;");
    }

    /**
     * Adds ScrollPane, Textfield, Button to main layout.
     */
    public void setMainLayout() {
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);

    }

    /**
     * Sets up scroll pane for user interface.
     */
    public void setScrollPane() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
    }

    /**
     * Sets up Textfield that allows for user input.
     * @param bot A duke object.
     */
    public void setUserInput(Duke bot) {
        userInput.setPrefWidth(325.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        userInput.setOnAction((event) -> handleUserInput(bot));
    }

    /**
     * Sets up Button that allows submission of user input.
     * @param bot A duke object.
     */
    public void setSendButton(Duke bot) {
        sendButton.setPrefWidth(55.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        sendButton.setOnMouseClicked((event) -> handleUserInput(bot));
    }

    private void handleUserInput(Duke bot) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label();
        String input = userInput.getText();
        String response;
        Command userCommand;
        try {
            userCommand = Parser.processInput(input, bot);
            response = Main.runUserCommand(userCommand, bot);
            if (userCommand instanceof ByeCommand) {
                Platform.exit();
            }
        } catch (InvalidCommandException | InvalidArgumentException | IOException ex) {
            response = ex.getMessage();
        }

        dukeText.setText(response);
        userText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        dukeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        Label line = new Label("\u2500".repeat(50));
        line.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke)),
                line
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return input;
    }

    /**
     * Returns scene of application.
     * @return Scene of application.
     */
    public Scene getScene() {
        return scene;
    }
}
