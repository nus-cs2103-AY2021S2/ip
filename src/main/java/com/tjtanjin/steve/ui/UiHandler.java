package com.tjtanjin.steve.ui;

import com.tjtanjin.steve.parser.Parser;

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

/**
 * UiHandler sits between the user and the parser, working directly with both parties to communicate
 * responses.
 */
public class UiHandler {

    private static final VBox dialogContainer = new VBox();
    private static ScrollPane scrollPane;
    private static TextField userInput;
    private final Parser parser;
    private final Image user = new Image(getClass().getResourceAsStream("/images/user.png"));
    private final Image steve = new Image(getClass().getResourceAsStream("/images/steve.png"));

    /**
     * Constructor for UiHandler.
     * @param parser parser to process user input
     */
    public UiHandler(Parser parser) {
        this.parser = parser;
    }

    /**
     * Shows the window of the application to the user.
     * @param stage stage to show user
     */
    public void showMainScreen(Stage stage) {

        //initialize scroll pane, user input, send button and main layout
        scrollPane = new ScrollPane();
        userInput = new TextField();
        Button sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        //initialize and setup scene
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        //setup default height and width
        double windowMinHeight = 700;
        double windowMinWidth = 500;
        double windowMaxHeight = 1400;
        double windowMaxWidth = 1000;
        double dialogContainerMinWidth = windowMinWidth - 175;
        double dialogContainerMaxWidth = windowMaxWidth - 175;

        //setup main window
        stage.setTitle("Steve");
        stage.setResizable(false);
        stage.setMinHeight(windowMinHeight);
        stage.setMinWidth(windowMinWidth);
        stage.setMaxHeight(windowMaxHeight);
        stage.setMaxWidth(windowMaxWidth);
        mainLayout.setPrefSize(windowMinWidth, windowMinHeight);

        //setup dialog container (chat messages)
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setPrefWidth(dialogContainerMinWidth);
        dialogContainer.setMinWidth(dialogContainerMinWidth);
        dialogContainer.setMaxWidth(dialogContainerMaxWidth);

        //setup user input text area
        userInput.setPrefWidth(windowMinWidth - 75);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //setup send button
        sendButton.setPrefWidth(70.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        //setup scroll pane and scrollbar
        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(windowMinWidth - 15, windowMinHeight - 65);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //captures user input and calls the function responsible for handling it
        sendButton.setOnMouseClicked((event) -> handleUserInput(userInput.getText()));
        userInput.setOnAction((event) -> handleUserInput(userInput.getText()));
    }

    /**
     * Greets the user upon program launch.
     */
    public void showWelcome() {
        showInfo("Hello, I am Steve! How can I help you?");
    }

    /**
     * Prints information to the user
     * @param info information to print
     */
    public void showInfo(String info) {
        Label text = new Label(info);
        dialogContainer.getChildren().add(DialogBox.getSteveDialog(text, new ImageView(steve)));
    }

    /**
     * Prints error message to the user
     * @param msg error message to print
     */
    public void showError(String msg) {
        Label text = new Label(msg);
        dialogContainer.getChildren().add(DialogBox.getSteveDialog(text, new ImageView(steve)));
    }

    /**
     * Handles the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(String input) {
        Label text = new Label("\n" + userInput.getText());
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(text, new ImageView(user))
        );
        userInput.clear();
        showResponse(parser.parseInput(input));
    }

    /**
     * Checks response and print as info or error.
     * @param response error message to print
     */
    public void showResponse(String response) {
        if (response.startsWith("Error:")) {
            showError(response);
        } else {
            showInfo(response);
        }
    }

    /**
     * Terminates the program with a final message.
     * @param crashMsg message to print before termination
     */
    public static void terminate(String crashMsg) {
        System.out.println(crashMsg);
        System.exit(0);
    }
}
