package ekud.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A DialogueBox is a single row on the GUI containing the avatar and message.
 */
public class DialogueBox extends HBox {
    @FXML
    private Label messageBubble;
    @FXML
    private ImageView profilePicture;

    /**
     * @param message The message of the text.
     * @param image   The profile image of the sender.
     */
    public DialogueBox(String message, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/DialogueBubble.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageBubble.setText(message);
        profilePicture.setImage(image);
    }

    /**
     * Generate a DialogueBox for messages sent by the user.
     *
     * @param message   The message sent by the user.
     * @param userImage The profile image of the user.
     * @return The generated DialogueBox.
     */
    public static DialogueBox genUserDialogue(String message, Image userImage) {
        return new DialogueBox(message, userImage);
    }

    /**
     * Generate a DialogueBox for replies by Ekud, has opposite alignment.
     *
     * @param message   The reply from Ekud.
     * @param userImage The profile image of Ekud.
     * @return The generated DialogueBox.
     */
    public static DialogueBox genEkudDialogue(String message, Image userImage) {
        return new DialogueBox(message, userImage).flip();
    }

    /**
     * Flip the content of the DialogueBox to become left-to-right.
     *
     * @return The current DialogueBox.
     */
    private DialogueBox flip() {
        ObservableList<Node> list = FXCollections.observableArrayList(getChildren());
        Collections.reverse(list);
        getChildren().setAll(list);
        setAlignment(Pos.TOP_LEFT);
        return this;
    }
}
