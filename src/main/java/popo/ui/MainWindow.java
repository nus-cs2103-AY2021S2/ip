package popo.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import popo.commands.Command;
import popo.commands.CommandResult;
import popo.commands.InvalidCommandException;
import popo.commands.InvalidDescriptionException;
import popo.commands.NoDescriptionException;
import popo.parser.Parser;
import popo.storage.Storage;
import popo.storage.StorageException;
import popo.tasks.TaskList;

/**
 * The Main Window. Provides the basic application layout.
 */
public class MainWindow extends VBox {
    private Storage storage;
    private TaskList taskList;
    private Stage primaryStage;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private HBox bottomContainer;

    private Image userImage;
    private Image popoImage;

    /**
     * Initializes the required components and sets up the avatar icons.
     */
    @FXML
    public void initialize() {
        setUpAvatarIcons();
        automateScrolling();
        setUpContainerBackground();
    }

    private void setUpAvatarIcons() {
        userImage = new Image(getClass().getResourceAsStream("/images/user.png"));
        popoImage = new Image(getClass().getResourceAsStream("/images/popo.png"));
    }

    private void automateScrolling() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    private void setUpContainerBackground() {
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        bottomContainer.setBackground(background);
    }

    /**
     * Sets up the required components for the MainWindow.
     *
     * @param storage      {@code Storage} object that handles file operations.
     * @param taskList     The loaded task list from storage.
     * @param primaryStage The primary stage of the application.
     */
    public void setComponents(Storage storage, TaskList taskList, Stage primaryStage) {
        this.storage = storage;
        this.taskList = taskList;
        this.primaryStage = primaryStage;
    }

    /**
     * Adds the welcome message label.
     */
    public void addWelcomeMessage() {
        String welcomeMsg = "Hello! I'm Popo, a personal assistant for managing task.\nWhat can I do for you?";
        DialogBox greetingDialog = DialogBox.getPopoDialog(welcomeMsg, popoImage);
        dialogContainer.getChildren().add(greetingDialog);
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPopoDialog(response, popoImage)
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
            if (commandResult.isExitingProgram()) {
                handleExit();
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
     * @param command User command.
     * @return Result command.
     */
    private CommandResult executeCommand(Command command) {
        command.setTaskList(taskList);
        return command.execute();
    }

    /**
     * Updates the cached task list if it was modified by the previous command.
     *
     * @param taskList Updated task list.
     */
    private void updateTaskListIfPresent(TaskList taskList) {
        if (taskList != null) {
            this.taskList = taskList;
        }
    }

    private void handleExit() {
        primaryStage.hide();
        System.exit(0);
    }
}
