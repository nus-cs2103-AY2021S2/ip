package dukegui;

import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import dukebody.Duke;

public class DukeApp extends Application {
    // nodes
    private ScrollPane scrollpane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // data members
    private Duke dukeCallback = new Duke();
    private Image userFace = new Image(this.getClass().getResourceAsStream(
            "/images/DefaultUser.png"));;

    private Image dukeFace = new Image(this.getClass().getResourceAsStream(
            "/images/DogeDuke.png"));

    private DateTimeFormatter dateformat = DateTimeFormatter.ofPattern(
            "eee, dd MMM yyyy HH:mm a");

    @Override
    public void start(Stage stage) {
        // set-up nodes
        scrollpane = new ScrollPane();
        dialogContainer = new VBox();
        scrollpane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollpane, userInput, sendButton);

        scene = new Scene(mainLayout);

        // set-up window
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        // set-up mainLayout
        mainLayout.setPrefSize(400.0, 600.0);

        // set-up scroll pane
        scrollpane.setPrefSize(400.0, 550.0);
        scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollpane.setVvalue(1.0);
        scrollpane.setFitToWidth(true);
        AnchorPane.setTopAnchor(scrollpane, 1.0);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) ->
                scrollpane.setVvalue(1.0));

        // set-up user input
        userInput.setPrefSize(335.0, 45.0);

        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);

        // set-up send button
        sendButton.setPrefSize(60.0, 45.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        stage.setScene(scene);
        stage.show();

        syncDuke();
        dukeOutput("Im Doge, who are you? type your username!");
    }

    // accessors
    public DateTimeFormatter getDateformat () {
        return dateformat;
    }

    // mutators
    private void syncDuke () {
        dukeCallback.syncDukeApp(this);

        userInput.setOnAction((event) -> {
            if (dukeCallback.hasSetupUser()) {
                dukeCallback.respondToCommand(pushUserInput());
            } else{
                dukeCallback.userSetup(pushUserInput());
            }
        });

        sendButton.setOnMouseClicked((event) -> {
            if (dukeCallback.hasSetupUser()) {
                dukeCallback.respondToCommand(pushUserInput());
            } else{
                dukeCallback.userSetup(pushUserInput());
            }
        });
    }

    private String pushUserInput () {
        String input = userInput.getText();
        dialogContainer.getChildren().add(new DialogBox(new Label(input),
                new ImageView(userFace)));

        userInput.clear();
        return input;
    }

    public void dukeOutput (String message) {
        dialogContainer.getChildren().add(new DialogBox(
                new Label(message), new ImageView(dukeFace), true
        ));
    }

    public void changeDateformat (String formatExpression) {
        dateformat = DateTimeFormatter.ofPattern(formatExpression);
    }
}
