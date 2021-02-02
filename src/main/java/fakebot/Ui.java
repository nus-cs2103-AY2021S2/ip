package fakebot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fakebot.command.Command;
import fakebot.command.CommandException;
import fakebot.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        //Step 2. Formatting the window to look as expected
        stage.setTitle("FakeBot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(500.0, 600.0);

        scrollPane.setPrefSize(485, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(425.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            processUserInput(userInput.getText());
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            processUserInput(userInput.getText());
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // more code to be added here later
        addTextToContainer(fakeBot.getHelloMessage());
        stage.setScene(scene);
        stage.show();

    }

    private void processUserInput(String userInput){
        Command command;
        try {
            command = fakeBot.validateCommand(userInput);
        } catch (CommandException e) {
            addTextToContainer(e.getMessage());
            return;
        }
        String text = fakeBot.processCommand(command);
        if(text.length() > 0) {
            addTextToContainer((text));
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
        dialogContainer.getChildren().add(getDialogLabel(text));
    }
    /**
     * Reads Line from IO.
     *
     * @return Return String read from input.
     */
    public String readLine() {
        String input = scanf.nextLine();
        return input;
    }
}
