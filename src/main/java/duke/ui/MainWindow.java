package duke.ui;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.commands.InvalidCommandException;
import duke.commands.InvalidDescriptionException;
import duke.commands.NoDescriptionException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.StorageException;
import duke.tasks.TaskList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

    private Image backgroundImage;
    private Image userImage;
    private Image dukeImage;

    /**
     * Sets up the avatar icons.
     */
    @FXML
    public void initialize() {
        // Credits: Pictures from http://locusanimation.com/project/runningman/?lang=en
        userImage = new Image(getClass().getResourceAsStream("/images/popo.png"));
        dukeImage = new Image(getClass().getResourceAsStream("/images/kuga.png"));

        // Set the scroll pane to automatically scroll down when the text reaches the bottom
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        bottomContainer.setBackground(background);
    }

    /**
     * Set up the required components for the MainWindow.
     *
     * @param storage {@code Storage} object that handles file operations.
     * @param taskList The loaded task list from storage.
     * @param primaryStage The primary stage of the application.
     */
    public void setComponents(Storage storage, TaskList taskList, Stage primaryStage) {
        this.storage = storage;
        this.taskList = taskList;
        this.primaryStage = primaryStage;
        loadAdditionalConstraints();
    }

    /**
     * Adds some constraints to the primary stage that shows the application and
     * adds the welcome message label.
     */
    public void loadAdditionalConstraints() {
        String welcomeMsg = "Hello! I'm Duke.\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMsg, dukeImage));

        primaryStage.setTitle("Duke");
        primaryStage.setMinWidth(500.0);
        primaryStage.setMinHeight(400.0);
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
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
     * Update the cached task list if it was modified by the previous command.
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
