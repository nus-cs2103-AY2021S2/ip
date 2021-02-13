package duke.gui;

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
 * This control represents a dialog box consisting of an ImageView
 * to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates dialog box.
     *
     * @param text text to be put in the dialog box
     * @param img image to be put in the dialog box
     * @param isUser whether the dialog box is for user
     */
    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        if (isUser) {
            dialog.setStyle("-fx-background-color: #8FBC8F");
        } else {
            dialog.setStyle("-fx-background-color: #A9A9A9");
        }
        displayPicture.setImage(img);
    }

    /**
     * Gets user's dialog box.
     *
     * @param text user input
     * @param img user image
     * @return user's dialog box
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img, true);
        return db;
    }

    /**
     * Gets duke's dialog box.
     *
     * @param text duke input
     * @param img duke image
     * @return duke's dialog box
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
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
}
