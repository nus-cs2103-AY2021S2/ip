package duke;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

public class DeadlineWindow {

    @FXML
    private AnchorPane window;
    @FXML
    private TextField userInput;
    @FXML
    private DatePicker userDate;
    @FXML
    private Label errorName;
    @FXML
    private Label errorDate;

    private Parser parser;

    public void setParser(Parser p) {
        assert(p != null);
        this.parser = p;
    }

    @FXML
    private void submit() {
        String name = userInput.getText();
        LocalDate date = userDate.getValue();
        boolean check = true;
        if (name.trim().isEmpty()) {
            errorName.setText("Name required");
            check = false;
        }
        if (date == null) {
            errorDate.setText("Date required");
            check = false;
        }
        if (check) {
            String command = name + " /by " + date;
            parser.parse("deadline " + command);
            window.getScene().getWindow().hide();
        }
    }
}
