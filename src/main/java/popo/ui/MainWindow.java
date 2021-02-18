package popo.ui;

import static popo.utils.Messages.MESSAGE_INVALID_INDEX;

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
import popo.commands.exceptions.InvalidCommandException;
import popo.commands.exceptions.InvalidDescriptionException;
import popo.commands.exceptions.NoDescriptionException;
import popo.parser.Parser;
import popo.storage.Storage;
import popo.storage.exceptions.StorageException;
import popo.tasks.TaskList;

/**
 * The Main Window. Provides the basic application layout.
 */
public class MainWindow extends VBox {
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

    private Storage storage;
    private TaskList taskList;
    private Stage primaryStage;
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
        DialogBox greetingDialog = DialogBox.getPopoDialog(welcomeMsg, popoImage, false);
        dialogContainer.getChildren().add(greetingDialog);
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        boolean isErrorMsg = false;
        try {
            Command command = new Parser().parseCommand(input);
            CommandResult commandResult = executeCommand(command);
            if (commandResult.isExitingProgram()) {
                exit();
            }
            storage.saveTasksIfPresent(commandResult.getUpdatedTaskList());
            updateTaskListIfPresent(commandResult.getUpdatedTaskList());
            response = commandResult.getMessageForUser();
        } catch (InvalidCommandException | StorageException | InvalidDescriptionException
                | NoDescriptionException ex) {
            response = ex.getMessage();
            isErrorMsg = true;
        }
        DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
        DialogBox popoDialog = DialogBox.getPopoDialog(response, popoImage, isErrorMsg);
        dialogContainer.getChildren().addAll(userDialog, popoDialog);
        userInput.clear();
    }

    private CommandResult executeCommand(Command command) throws InvalidDescriptionException {
        try {
            command.setTaskList(taskList);
            return command.execute();
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidDescriptionException(MESSAGE_INVALID_INDEX);
        }
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

    private void exit() {
        primaryStage.hide();
        System.exit(0);
    }
}
