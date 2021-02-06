package fakebot;

import java.util.Scanner;

import fakebot.command.Command;
import fakebot.command.CommandException;
import fakebot.ui.DialogBox;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * Ui Class use for reading and printing data.
 */
public class Ui extends Application {


    private Scanner scanf;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    AnchorPane mainLayout;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Logo.png"));
    private Image fakeBotImage = new Image(this.getClass().getResourceAsStream("/images/wolf.png"));

    private FakeBot fakeBot;

    /**
     * Class constructor for Ui.
     */
    public Ui() {
        scanf = new Scanner(System.in);

        fakeBot = new FakeBot();
    }

    //Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html
    @Override
    public void start(Stage stage) {
        setupRequiredComponents();
        FormateWindow(stage);
        AddListener();

        //Creating hello text
        addTextToContainer(fakeBot.getHelloMessage());
        stage.setScene(scene);
        stage.show();

    }

    /**
     * setup required Ui components.
     */
    private void setupRequiredComponents() {
        //Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
    }

    /**
     * Add Event Listener.
     */
    private void AddListener() {
        //Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(userInput.getText());
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            handleUserInput(userInput.getText());
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Formates the window to look as expected.
     * 
     * @param stage Window Stage.
     */
    private void FormateWindow(Stage stage) {
        stage.setTitle("FakeBot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(600.0, 600.0);

        scrollPane.setPrefSize(585, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(525.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Handle User Input.
     * @param userInput User Input String.
     */
    private void handleUserInput(String userInput) {
        Command command;
        try {
            command = Parser.parseUserInputToCommand(userInput);
            String outputText = fakeBot.processCommand(command);

            if (outputText.length() > 0) {
                addTextToContainer(userInput, outputText);
            } else {
                Stage stage = (Stage) scene.getWindow();
                stage.close();
            }

        } catch (CommandException e) {
            addTextToContainer(userInput, e.getMessage());
        }
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setFont(Font.font ("Courier New", 12));
        textToAdd.setWrapText(true);

        return textToAdd;
    }


    /**
     * Add text to container
     *
     * @param text text to be add to Container
     */
    private void addTextToContainer(String text) {
        Label userText = getDialogLabel(text);
        dialogContainer.getChildren().add(DialogBox.getFakebotDialog(userText, new ImageView(fakeBotImage)));
    }

    /**
     * Add text to container
     *
     * @param inputText inputText to be add to Container
     * @param outputText outputText to be add to Container
     */
    private void addTextToContainer(String inputText, String outputText) {

        Label userText = getDialogLabel(inputText);
        Label fakebotText = getDialogLabel(outputText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getFakebotDialog(fakebotText, new ImageView(fakeBotImage))
        );
    }
}
