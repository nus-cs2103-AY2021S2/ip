package duke.controller;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

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

    /**
     * Initialises a DialogBox object.
     *
     * @param text Description of text
     * @param img Image of User/Duke.
     */
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
     * Returns a dialog box for the user.
     *
     * @param text Description of the input.
     * @param img Image of the user.
     * @return a DialogBox for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        BackgroundFill bgFill = new BackgroundFill(Color.rgb(229, 242, 255), CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(bgFill);
        db.setBackground(bg);
        return db;
    }

    /**
     * Returns a dialog box for Duke.
     *
     * @param text Description of the output.
     * @param img Image of Duke.
     * @return a DialogBox for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        BackgroundFill bgFill = new BackgroundFill(Color.rgb(255, 229, 236), CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(bgFill);
        db.setBackground(bg);
        return db;
    }
}
