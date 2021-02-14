package duke.controller;

import java.time.LocalDate;

import duke.Duke;
import duke.command.Command;
import duke.parser.Parser;
import duke.task.Task;
import duke.tasklist.TaskList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private ScrollPane reminderScrollPane;
    @FXML
    private VBox reminderContainer;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        reminderScrollPane.vvalueProperty().bind(reminderContainer.heightProperty());
        showMessage("Welcome to Duke! What can I do for you today?");
    }

    public void setDuke(Duke d) {
        duke = d;
        TaskList taskList = duke.getTaskList();
        showReminder("Reminders:");
        showAllReminder(taskList);
    }

    private void showMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage));
    }

    private void showReminder(String message) {
        reminderContainer.getChildren().add(ReminderBox.getReminder(message));
    }

    private void showAllReminder(TaskList taskList) {
        LocalDate currentDate = LocalDate.now();
        for (Task task : taskList.getTaskList()) {
            showReminder(task.toString());
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String outputMessage = duke.runEachInput(input);

        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();

        showMessage(outputMessage);

        if (input.equals("bye")) {
            System.exit(0);
        }
    }
}
