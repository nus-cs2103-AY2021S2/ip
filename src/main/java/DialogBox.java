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
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(Image img, Label l) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(l.getText());
        dialog.setWrapText(true);
        displayPicture.setImage(img);
        setHeight(l);
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
     * ensures text bubble fits the length of long inputs
     *
     * @param l Label generated from duke response
     */
    private void setHeight(Label l) {
        int count = l.getText().endsWith("\n") ? 1 : 0;
        String[] ss = l.getText().split("\n");
        count += ss.length + 1;
        for (String s : ss) {
            count += s.length() / 32;
        }
        this.setMinHeight(count * 15 + 50);
    }

    public static DialogBox getUserDialog(Label l, Image img) {
        return new DialogBox(img, l);
    }

    public static DialogBox getDukeDialog(Label l, Image img) {
        var db = new DialogBox(img, l);
        db.flip();
        return db;
    }
}