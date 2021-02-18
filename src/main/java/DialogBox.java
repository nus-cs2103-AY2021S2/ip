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
    private Label nameLabel, dialog;
    @FXML
    private ImageView displayPicture;

    private String resourceName = "/view/DialogBox.fxml";

    public DialogBox(String name, String text, Image img, String resourceName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(resourceName));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        nameLabel.setText(name);
        dialog.setText(text);
        displayPicture.setImage(img);
    }

//    /**
//     * Flips the dialog box such that the ImageView is on the left and text on the right.
//     */
//    private void flip() {
//        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
//        Collections.reverse(tmp);
//        getChildren().setAll(tmp);
//        setAlignment(Pos.TOP_LEFT);
//    }

    public static DialogBox getDialog(String name, String text, Image img, String resourceName) {
        return new DialogBox(name, text, img, resourceName);
    }

//    public static UserDialogBox getUserDialog(String text, Image img) {
//        return new UserDialogBox("You", text, img);
//    }
//
//    public static DialogBox getDukeDialog(String text, Image img) {
//        var db = new DialogBox("Don", text, img);
////        db.flip();
//        return db;
//    }
}