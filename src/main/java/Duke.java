import java.util.ArrayList;
import java.util.Scanner;

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



public class Duke extends Application {


    protected static boolean canExit = false;
    private static final ArrayList<Task> taskList = new ArrayList<Task>();
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button exitButton;
    private Button sendButton;
    private Scene scene;
    private final Image user = new Image(this.getClass().getResourceAsStream("DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("DaDuke.png"));

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(taskList, ui);
        parser = new Parser(taskList, tasks, ui);
        storage.taskRecorder();
        storage.load(taskList);
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage("duke.txt");
        tasks = new TaskList(taskList, ui);
        parser = new Parser(taskList, tasks, ui);
        storage.taskRecorder();
        storage.load(taskList);
    }


    public String run() throws InvalidCommandException {
        Scanner sc = new Scanner(System.in);
        String description = sc.nextLine();
        return parser.readCommand(description);
    }


    public static void main(String[] args) {
        String path = "duke.txt";
        Duke duke = new Duke(path);
        duke.storage.taskHistory();

        while (!canExit) {
            try {
                duke.run();
            } catch (InvalidCommandException e) {
                System.out.println(e.toString());
            }
        }

        duke.storage.record(taskList);
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        scrollPane.setStyle("-fx-background: #FEE1E8 ; -fx-background-color: #FEE1E8;");

        userInput = new TextField();
        sendButton = new Button("Enter");
        exitButton = new Button("Exit");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setStyle("-fx-background-color: #FEE1E8;");
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, exitButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("\u2764小茜的待办事项\u2764");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);
        exitButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        AnchorPane.setTopAnchor(exitButton, 1.0);
        AnchorPane.setLeftAnchor(exitButton, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        greetUser();

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (InvalidCommandException e) {
                handleErrorInput(e.toString());
                userInput.clear();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (InvalidCommandException e) {
                handleErrorInput(e.toString());
                userInput.clear();
            }
        });

        exitButton.setOnAction((event) -> {
            storage.record(taskList);
            Platform.exit();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    @Override
    public void stop() {

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws InvalidCommandException {
        String input = userInput.getText();
        String response = parser.readCommand(input);
        Label userText = new Label("Kuromi: " + input);
        Label dukeText = new Label(getResponse(response));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
        userInput.clear();
    }

    private void greetUser() {
        Label dukeText = new Label(getResponse(ui.greet(storage)));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
    }


    private void handleErrorInput(String errorMsg) {
        Label dukeText = new Label(getResponse(errorMsg));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Kitty: " + input;
    }
}
