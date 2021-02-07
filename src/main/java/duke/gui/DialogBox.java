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
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cut height instead of width
        Circle clip = new Circle(displayPicture.getFitWidth() / 2);
        clip.setCenterX(displayPicture.getFitWidth() / 2);
        clip.setCenterY(displayPicture.getFitHeight() / 2);
        displayPicture.setClip(clip);

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Recursively flips the dialog box so that its HBox children are all flipped. This is to have the ImageView on the
     * left and text on the right.
     */
    private void flip() {
        DialogBox.flip(this);
    }

    /**
     * Helper method to recursively flip children in HBox.
     *
     * @param hBox hBox to flip
     */
    private static void flip(HBox hBox) {
        ObservableList<Node> tmp = FXCollections.observableArrayList(hBox.getChildren());

        for (Node n: tmp) {
            if (n instanceof HBox) {
                DialogBox.flip((HBox) n);
            }
        }

        Collections.reverse(tmp);
        hBox.getChildren().setAll(tmp);
        hBox.setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
