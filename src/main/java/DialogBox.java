
// Solution below are adapted from https://se-education.org/guides/tutorials/javaFx.html
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
 * An example of a custom control using FXML. This control represents a dialog
 * box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, boolean isUser) {
        FXMLLoader fxmlLoader;
        if (isUser) {
            fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBoxUser.fxml"));
        } else {
            fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
        }
        try {
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
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    /**
     * Creates the dialog box for Duke's reply.
     * @param text the text output.
     * @param img  duke's image
     * @return
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }
}
