package main.java;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * DialogBox class containing profile photo ImageView and conversation Label
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Create a dialog box containing a profile photo and a conversation
     * @param l conversation Label
     * @param iv profile photo ImageView
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        text.setWrapText(true);
        AnchorPane dialogLayout = new AnchorPane();
        displayPicture.setFitWidth(60.0);
        displayPicture.setFitHeight(60.0);
        dialogLayout.getChildren().addAll(displayPicture, text);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Get the user format dialog
     * profile photo on the right and conversation on the left
     * @param l conversation Label
     * @param iv profile photo ImageView
     * @return user format DialogBox
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Get the flipped duke format dialog
     * profile photo on the left and conversation on the right
     * @param l conversation Label
     * @param iv profile photo ImageView
     * @return duke format DialogBox
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
