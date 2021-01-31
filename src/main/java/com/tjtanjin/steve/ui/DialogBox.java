//Solution taken from https://se-education.org/guides/tutorials/javaFx.html

package com.tjtanjin.steve.ui;

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
 * DialogBox is a custom control created to display chat messages in Steve.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     *
     * @param text chat message
     * @param img image of the sender/receiver
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(UiHandler.class.getResource("/view/DialogBox.fxml"));
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
     * Gets the dialog box for user.
     *
     * @param msg chat message of user
     * @param img image of user
     * @return dialog box for user
     */
    public static DialogBox getUserDialog(String msg, Image img) {
        return new DialogBox(msg, img);
    }

    /**
     * Gets the dialog box for Steve.
     *
     * @param msg chat message of steve
     * @param img image of steve
     * @return dialog box for steve
     */
    public static DialogBox getSteveDialog(String msg, Image img) {
        var db = new DialogBox(msg, img);
        db.flip();
        return db;
    }
}
