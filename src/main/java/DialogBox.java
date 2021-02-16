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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    private static final int USER_DIALOG_HEIGHT = 100;
    private static final int DUKE_DIALOG_HEIGHT = 180;

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
        displayPicture.setImage(img);
    }

    private Label getDialog() {
        return dialog;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates and returns DialogBox showing the user's input.
     * @param text User input.
     * @param img User image icon.
     * @return DialogBox showing the user's input with their image icon.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setMinHeight(USER_DIALOG_HEIGHT);
        return db;
    }

    /**
     * Creates and returns DialogBox showing Duke's response.
     * @param text Duke response.
     * @param img Duke image icon.
     * @return DialogBox showing Duke's response with its image icon.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setMinHeight(DUKE_DIALOG_HEIGHT);
        db.flip();
        db.getDialog().setEllipsisString("\n(...and more!)");
        Font dukeFont = new Font("Consolas", 11);
        db.getDialog().setFont(dukeFont);
        return db;
    }

    /**
     * Creates and returns DialogBox showing an error response from Duke.
     * @param text Duke error response.
     * @param img Duke image icon.
     * @return DialogBox showing Duke's error response with its image icon.
     */
    public static DialogBox getDukeErrorDialog(String text, Image img) {
        var db = getDukeDialog(text, img);
        Color warningColor = Color.RED;
        db.getDialog().setTextFill(warningColor);
        return db;
    }
}