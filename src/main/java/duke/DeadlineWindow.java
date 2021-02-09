package duke;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DeadlineWindow {

    @FXML
    private AnchorPane window;
    @FXML
    private TextField userInput;
    @FXML
    private DatePicker userDate;

    private Parser parser;

    public void setParser(Parser p) {
        assert(p != null);
        this.parser = p;
    }

    @FXML
    private void submit() {
        String command = userInput.getText() + " /by " + userDate.getValue();
        parser.parser("deadline " + command);
        window.getScene().getWindow().hide();
    }
}
