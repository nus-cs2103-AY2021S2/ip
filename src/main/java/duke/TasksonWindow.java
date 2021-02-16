package duke;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

public class TasksonWindow {

    @FXML
    private AnchorPane window;
    @FXML
    private DatePicker userDate;
    @FXML
    private Label errorDate;

    private Parser parser;

    public void setParser(Parser p) {
        this.parser = p;
    }

    @FXML
    private void submit() {
        LocalDate date = userDate.getValue();
        if (date == null) {
            errorDate.setText("Date required");
        } else {
            String command = "taskson " + date;
            parser.chat(command);
            window.getScene().getWindow().hide();
        }
    }
}
