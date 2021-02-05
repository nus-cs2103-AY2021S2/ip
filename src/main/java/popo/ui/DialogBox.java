package popo.ui;

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
 * Represents a dialogue box containing an avatar icon and the text message.
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Color backgroundColor = Color.rgb(250, 200, 200, 0.9);
        CornerRadii cornerRadii = new CornerRadii(5);
        Insets insets = new Insets(-4);
        BackgroundFill backgroundFill = new BackgroundFill(backgroundColor, cornerRadii, insets);
        Background background = new Background(backgroundFill);
        dialog.setBackground(background);
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Creates a user dialogue box with the given user message text and the avatar icon.
     *
     * @param text Text message.
     * @param img Avatar icon image.
     * @return {@code DialogBox} representing a user command.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialogue box with the given message text and the avatar icon.
     *
     * @param text Text message.
     * @param img Avatar icon image.
     * @return {@code DialogBox} representing a reply.
     */
    public static DialogBox getPopoDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }
}
