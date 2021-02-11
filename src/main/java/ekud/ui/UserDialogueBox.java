package ekud.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A DialogueBox is a single row on the GUI containing the avatar and message.
 */
public class UserDialogueBox extends HBox {
    @FXML
    private Label messageBubble;
    @FXML
    private ImageView profilePicture;

    /**
     * @param message The message of the text.
     */
    public UserDialogueBox(String message) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/UserDialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageBubble.setText(message);
    }
}
