package duke.window;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle profilePic;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setTextFill(Color.WHITE);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        profilePic.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Creates a DialogBox to display a message sent by the user.
     * @param text input from the user.
     * @param img user's profile picture.
     * @return a DialogBox representing a message sent by the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.dialog.setBackground(new Background(new BackgroundFill(Color.NAVY, null, null)));
        return dialogBox;
    }

    /**
     * Creates a DialogBox to display a message from the chatbot.
     * @param text response from the chatbot.
     * @param img chatbot's profile picture.
     * @return a DialogBox representing a message sent by the chatbot.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.dialog.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, null, null)));
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Creates a DialogBox to display an error message from the chatbot.
     * @param text error message from the chatbot.
     * @param img chatbot's profile picture.
     * @return a DialogBox representing an error message from the chatbot.
     */
    public static DialogBox getDukeErrorDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.flip();
        dialogBox.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
        dialogBox.dialog.setTextFill(Color.BLACK);
        return dialogBox;
    }
}
