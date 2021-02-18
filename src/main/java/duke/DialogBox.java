package duke;

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
import javafx.scene.layout.VBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends VBox {
    @FXML
    private Label username;

    private DialogBox(String name, InnerDialogBox idb) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        username.setText(name);
        this.getChildren().addAll(idb);
    }


    public static DialogBox getUserDialog(String text, Image img, String name) {
        return new DialogBox(name, InnerDialogBox.getInnerUserDialog(text, img));
    }

    public static DialogBox getDukeDialog(String text, Image img, String name) {
        DialogBox db = new DialogBox(name, InnerDialogBox.getInnerDukeDialog(text, img));
        db.setAlignment(Pos.TOP_LEFT);
        return db;
    }
}
