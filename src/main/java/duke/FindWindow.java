package duke;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FindWindow {

    @FXML
    private AnchorPane window;
    @FXML
    private TextField userInput;

    private Parser parser;

    public void setParser(Parser p) {
        assert(p != null);
        this.parser = p;
    }

    @FXML
    private void submit() {
        String command = userInput.getText();
        //text.setText(parser.chat(command));
        parser.chat("find " + command);
        window.getScene().getWindow().hide();
    }
}
