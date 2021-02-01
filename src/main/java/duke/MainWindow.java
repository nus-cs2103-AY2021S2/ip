package duke;

import duke.commands.*;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.StorageException;
import duke.tasks.TaskList;
import duke.ui.DialogBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    
    private Image userImage;
    private Image dukeImage;

    /**
     * This method is called to initialize a controller after its root element has been completely processed.
     */
    @FXML
    public void initialize() {
        userImage = new Image(getClass().getResourceAsStream("/images/user.png"));
        dukeImage = new Image(getClass().getResourceAsStream("/images/duke.png"));
        // Set the scroll pane to automatically scroll down when the text reaches the bottom
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }
    
    public void setComponents(Storage storage, TaskList taskList, Stage primaryStage) {
        this.storage = storage;
        this.taskList = taskList;
        this.primaryStage = primaryStage;
        loadAdditionalConstraints();
    }

    public void loadAdditionalConstraints() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // Create a new Label control
        String welcomeMsg = "Hello! I'm Duke.\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMsg, dukeImage));

        primaryStage.setTitle("Duke");
        primaryStage.setMinWidth(400.0);
        primaryStage.setMinHeight(500.0);
        
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
    
    private void handleExit() {
        primaryStage.hide();
        System.exit(0);
    }
}
