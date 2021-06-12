package duke.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Represents the reminder box at the top of the duke GUI to remind users of their latest tasks added to the list
 */
public class ReminderBox extends HBox {
    @FXML
    private Label reminder;

    private ReminderBox(String taskDescription) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ReminderBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reminder.setText(taskDescription);
    }

    /**
     * @param taskDescription description of task
     * @return a Hbox object to store reminders
     */
    public static ReminderBox getReminder(String taskDescription) {
        return new ReminderBox(taskDescription);
    }
}
