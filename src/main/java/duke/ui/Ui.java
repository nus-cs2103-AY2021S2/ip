package duke.ui;

import duke.DialogBox;

import duke.commands.*;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.StorageException;
import duke.tasks.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Handles the input/output of the application.
 * Responsible for getting user input and printing messages to the console.
 */
public class Ui {
    private static final String DIVIDER = "------------------------------------------------------------";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private Storage storage;
    private TaskList taskList;
    
    private Stage primaryStage;
    private AnchorPane mainLayout;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    
    public Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }
    
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.show();
        loadUiComponents();
    }

    public void loadUiComponents() {
        // Create a new Label control
        Label welcomeMsg = new Label(String.format("Hello! I'm\n%s\nWhat can I do for you?", LOGO));
        welcomeMsg.setWrapText(true);

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(welcomeMsg, scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinWidth(400.0);
        primaryStage.setMinHeight(600.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385.0, 535.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // setting vvalue to vmax so that the scroll position is at the bottom initially
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

        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMsg));
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText),
                DialogBox.getDukeDialog(dukeText)
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        try {
            // Parse the user input into an executable command
            Command command = new Parser().parseCommand(input);

            // Execute the command
            CommandResult commandResult = executeCommand(command);
            
            // If the command is to exit the program
            if (commandResult.getMessageForUser().equals("Exiting...")) {
                primaryStage.hide();
                System.exit(0);
            }

            // Update the cached task list and save it to file
            storage.saveTasksIfPresent(commandResult.getUpdatedTaskList());
            updateTaskListIfPresent(commandResult.getUpdatedTaskList());

            // Return the message for the user
            return commandResult.getMessageForUser();
        } catch (InvalidCommandException | StorageException | InvalidDescriptionException
                | NoDescriptionException ex) {
            return ex.getMessage();
        }
    }

    /**
     * Executes the command and return a CommandResult instance.
     *
     * @param command user command
     * @return result command
     */
    private CommandResult executeCommand(Command command) {
        command.setTaskList(taskList);
        return command.execute();
    }

    /**
     * Update the cached task list if it was modified by the previous command.
     *
     * @param taskList updated task list
     */
    private void updateTaskListIfPresent(TaskList taskList) {
        if (taskList != null) {
            this.taskList = taskList;
        }
    }
}
