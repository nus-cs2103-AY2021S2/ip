package jaryl.duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke chatbot for CS2103T Individual Project
 * @author LOH FAH YAO, JARYL
 */
public class Duke extends Application {
    private static final String FILE_PATH = "./data/duke.txt";
    private DataManager dataManager;
    private ArrayList<Task> tasksList;
    private Output output;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructor to instantiate a new Duke object
     */
    public Duke() {
        this.output = new Output();
        dataManager = new DataManager(FILE_PATH);

        try {
            tasksList = dataManager.readFromFile();
        } catch (DukeException e) {
            tasksList = new ArrayList<>();
            System.out.println(e.getMessage());
        }
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
        stage.setMinHeight(500.0);
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


    }

    private void handleUserInput() {
        Label userResponse = new Label(userInput.getText());
        Label dukeResponse = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                Dialog.getUserResponse(userResponse.toString()),
                Dialog.getDukeResponse(dukeResponse.toString())
        );
        userInput.clear();
    }

    protected String getResponse(String input) {
        return this.run(input);
    }

    /**
     * Entry point into Duke chatbot
     */
    public String run(String input) {
        String resp = "";
        output.printWelcomeMsg();

        try {
            Command cmd = Command.valueOf(input.split(" ")[0].toUpperCase());

            switch (cmd) {
                case EXIT:
                    output.printByeMsg();
                    break;
                case LIST:
                    output.listAction(tasksList);
                    break;
                case DONE:
                    output.doneAction(tasksList, input, dataManager);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    output.addAction(tasksList, input, dataManager);
                    break;
                case DELETE:
                    output.deleteAction(tasksList, input, dataManager);
                    break;
                case FIND:
                    output.findAction(tasksList, input);
                    break;
                case HELP:
                    output.sendHelp();
                    break;
            }
        } catch (IllegalArgumentException e) {
            output.printIllegalArgumentError();
        } catch (DukeException e1) {
            System.out.println(e1);
        }
        return resp;
    }
}