package chadbot.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            String filename = "/view/DialogBox.fxml";

            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(filename));
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
     * Creates a dialog box containing the user's input and avatar.
     *
     * @param text The user's input.
     * @param img The user's avatar.
     * @return The dialog box containing the user's input and avatar.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        BackgroundFill fill = new BackgroundFill(
                Color.web("#bec2cb"),
                new CornerRadii(45),
                Insets.EMPTY
        );
        db.setBackground(new Background(fill));
        return db;
    }

    /**
     * Creates a dialog box containing Duke's response and avatar.
     *
     * @param text Duke's response to the user input.
     * @param img Duke's avatar.
     * @return The dialog box containing Duke's response and avatar.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        BackgroundFill fill = new BackgroundFill(
                Color.web("#e5cfaa"),
                new CornerRadii(45),
                Insets.EMPTY
        );
        db.setBackground(new Background(fill));
        db.flip();
        return db;
    }

}
