package jaryl.duke;

import javafx.application.Application;
import javafx.application.Platform;
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
import java.util.ArrayList;

/**
 * Duke chatbot for CS2103T Individual Project
 * @author LOH FAH YAO, JARYL
 */
public class Duke extends Application {
    private static final String FILE_PATH = "./data/duke.txt";
    private DataManager dataManager;
    private ArrayList<Task> tasksList;
    private Output output;

    private TextField userInput;
    private VBox vBox;
    private ScrollPane scrollPane;
    private Button sendButton;

    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/ash.png"));
    private Image dukeImg = new Image(this.getClass().getResourceAsStream("/images/pikachu.png"));

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
        vBox = new VBox();
        scrollPane = new ScrollPane();

        scrollPane.setContent(vBox);

        sendButton = new Button(">");
        userInput = new TextField();

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(scrollPane, userInput, sendButton);

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinWidth(400.0);
        stage.setMinHeight(500.0);

        anchorPane.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        vBox.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        stage.setScene(new Scene(anchorPane));
        stage.show();

        userInput.setOnAction((event) -> {
            userInputHandler();
        });

        sendButton.setOnMouseClicked((event) -> {
            userInputHandler();
        });

        vBox.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void userInputHandler() {
        Label dukeResponse = new Label(getResponse(userInput.getText()));
        Label userResponse = new Label(userInput.getText());
        vBox.getChildren().addAll(
                Dialog.getUserResponse(userResponse.toString(), userImg),
                Dialog.getDukeResponse(dukeResponse.toString(), dukeImg)
        );
        userInput.clear();
    }

    protected String getResponse(String input) {
        assert !input.isEmpty() : "Please enter a valid input";
        return this.run(input);
    }

    /**
     * Entry point into Duke chatbot
     */
    public String run(String input) {
        String resp = output.printWelcomeMsg();

        try {
            Command cmd = Command.valueOf(input.split(" ")[0].toUpperCase());

            switch (cmd) {
                case EXIT:
                    Platform.exit();
                    System.exit(0);
                    break;
                case LIST:
                    resp = output.listAction(tasksList);
                    break;
                case DONE:
                    resp = output.doneAction(tasksList, input, dataManager);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    resp = output.addAction(tasksList, input, dataManager);
                    break;
                case DELETE:
                    resp = output.deleteAction(tasksList, input, dataManager);
                    break;
                case FIND:
                    resp = output.findAction(tasksList, input);
                    break;
                case HELP:
                    resp = output.sendHelp();
                    break;
            }
        } catch (IllegalArgumentException e) {
            resp = output.printIllegalArgumentError();
        } catch (DukeException e1) {
            resp = e1.toString();
        }
        return resp;
    }
}