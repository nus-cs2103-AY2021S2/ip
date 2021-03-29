package duke;

import java.io.IOException;
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
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor method
     * @throws IOException if user IO is incorrect
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/DukeData.txt");
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            tasks = new TaskList();
        }
        parser = new Parser(tasks, ui, storage);
    }

    /**
     * Constructor Method
     * @param filePath of data file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (Exception e) {
            tasks = new TaskList();
        }
        parser = new Parser(tasks, ui, storage);
    }


    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke ChatBot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        Label startMessage = new Label("Welcome to Duke Chatbot, How may I assist you?");

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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Method to get the Response of the chatbot
     * @param input command written by user
     * @return
     */
    public String getResponse(String input) {
        parser.insertCommand(input);
        String output = parser.process();
        if (input.equals("bye")) {
            Platform.exit();
        }
        return output;
    }

    /**
     * Method to run the Duke chatbot
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks, ui, storage);
        boolean isEnd = false;
        while (!isEnd) {
            try {
                String command = sc.nextLine();
                parser.insertCommand(command);
                parser.process();
                storage.saveTasks(tasks);
                if (command.equals("bye")) {
                    isEnd = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        storage.saveTasks(tasks);
        Platform.exit();
    }

    /**
     * Main method of Duke
     * @param args command line arguments
     */
    public static void main(String[] args) throws IOException {
        new Duke("data/DukeData.txt").run();
    }
}

