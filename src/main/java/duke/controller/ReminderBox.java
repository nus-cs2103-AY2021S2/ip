package duke.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

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

    public static ReminderBox getReminder(String taskDescription) {
        return new ReminderBox(taskDescription);
    }
}
