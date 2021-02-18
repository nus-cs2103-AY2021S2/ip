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
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView
 * to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    /**
     * Label of dialog.
     */
    @FXML
    private Label dialog;
    /**
     * displayPicture function of ImageView.
     */
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(
                    "/view/DialogBox.fxml"));
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
     * Flips the dialog box such that the ImageView is on the
     * left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(
                this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates User Dialog Box.
     *
     * @param text
     * @param img
     * @return a new dialog Box for the user
     */

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates Duke dialog box.
     *
     * @param text
     * @param img
     * @return a dialog Box for Duke
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Method for the Dialog box to display.
     *
     * @param text
     * @param img
     * @return the startup image and message
     */
    public static DialogBox getStartUpMessage(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.BASELINE_CENTER);
        return db;
    }
}
