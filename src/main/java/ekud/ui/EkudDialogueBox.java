package ekud.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Dialogue box meant for Ekud.
 */
public class EkudDialogueBox extends HBox {
    @FXML
    private Label messageBubble;

    /**
     * Construct a new dialogue box for Ekud.
     *
     * @param message The message of the text.
     */
    public EkudDialogueBox(String message) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/EkudDialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageBubble.setText(message);
    }
}
