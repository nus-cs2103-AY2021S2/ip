package soonkeatneo.duke;

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
 * Handles the dialog boxes that are used in the graphical interface of Duke.
 *
 * @author Soon Keat Neo
 * @version CS2103T AY20/21 Sem 2 iP v0.1
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     *
     * @param text message to be shown
     * @param img image of the sender or recipient
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
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
     * Gets the dialog box of the User.
     *
     * @param msg message the user sends to be shown
     * @param img image of the user
     * @return the dialog box of the user
     */
    public static DialogBox getUserDialog(String msg, Image img) {
        return new DialogBox(msg, img);
    }

    /**
     * Gets the dialog box of the Bot.
     *
     * @param msg message the bot will be returning
     * @param img image of the bot
     * @return the dialog box of the bot
     */
    public static DialogBox getDukeDialog(String msg, Image img) {
        var db = new DialogBox(msg, img);
        db.flip();
        return db;
    }
}
