package duke;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

public class TasksonWindow {

    @FXML
    private AnchorPane window;
    @FXML
    private DatePicker userDate;

    private Parser parser;

    public void setParser(Parser p) {
        this.parser = p;
    }

    @FXML
    private void submit() {
        String command = "taskson " + userDate.getValue();
        parser.chat(command);
        window.getScene().getWindow().hide();
    }
}
