package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DeleteWindow {

    @FXML
    private AnchorPane window;
    @FXML
    private TextField userInput;
    @FXML
    private Label errorInteger;
    @FXML
    private Label errorOutOfBounds;

    private Parser parser;

    public void setParser(Parser p) {
        assert(p != null);
        this.parser = p;
    }

    @FXML
    private void submit() {
        String command = userInput.getText();
        String output = parser.parse("delete " + command);
        if (command.trim().isEmpty()) {
            errorOutOfBounds.setText("");
            errorInteger.setText("Not an integer");
        } else if (output.equals(Ui.outOfArrayRange())) {
            errorInteger.setText("");
            errorOutOfBounds.setText("Out of bounds");
        } else if (output.equals(Ui.notAnInteger())) {
            errorOutOfBounds.setText("");
            errorInteger.setText(("Not an integer"));
        } else {
            window.getScene().getWindow().hide();
        }
    }
}
