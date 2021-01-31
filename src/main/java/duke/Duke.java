package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeLoadException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** A chat bot that can help the user manage their tasks */
public class Duke extends Application {
    /** Name of the chat bot */
    private static final String CHATBOT_NAME = "Mantaro";
    /** Determine whether the chat bot continue to run */
    private boolean isActive;
    /** Manages a task list in the chat bot */
    private TaskManager taskManager;
    /** Provide different way of printing messages for the chat bot */
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /** Constructor of Duke */
    public Duke() {
        isActive = true;

        ui = new Ui();
        taskManager = new TaskManager();
        Command.setup(ui, taskManager);

        try {
            Storage.loadTasksTo(taskManager);
        } catch (DukeLoadException e) {
            ui.printError(e.getMessage());
        }
    }

    /** Lifecycle of Duke */
    public void run() {
        ui.printWelcomeMsg(CHATBOT_NAME);

        Scanner scanner = new Scanner(System.in);
        String line = "";

        while (isActive) {
            line = scanner.nextLine();
            try {
                Command command = Parser.parse(line);
                command.execute();
                isActive = !command.willExit();
            } catch (DukeException e) {
                ui.printError(e.getMessage());
            }
        }

        ui.printGoodbyeMsg();
    }

    @Override
    public void start(Stage stage) {
        // Step 1
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Step 2
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

//    /**
//     * Lifecycle of the program
//     *
//     * @param args Command line arguments
//     */
//    public static void main(String[] args) {
//        Duke bot = new Duke();
//        bot.run();
//    }
}
