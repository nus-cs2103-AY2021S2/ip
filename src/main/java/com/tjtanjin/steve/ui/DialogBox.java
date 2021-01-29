//Solution taken from https://se-education.org/guides/tutorials/javaFx.html

package com.tjtanjin.steve.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * DialogBox is a custom control created to display chat messages in Steve.
 */
public class DialogBox extends HBox {

    /**
     * Constructor for DialogBox.
     * @param l label for the chat message
     * @param iv image of the sender/receiver
     */
    public DialogBox(Label l, ImageView iv) {

        l.setWrapText(true);
        iv.setFitWidth(100.0);
        iv.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, iv);
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
     * Gets the dialog box for user.
     * @param l chat message of user
     * @param iv image of user
     * @return dialog box for user
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Gets the dialog box for Steve.
     * @param l chat message of steve
     * @param iv image of steve
     * @return dialog box for steve
     */
    public static DialogBox getSteveDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
