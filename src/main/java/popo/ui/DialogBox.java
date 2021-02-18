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
    private static final Color COLOR_USER_DIALOG = Color.rgb(100, 252, 200, 0.9);
    private static final Color COLOR_POPO_DIALOG = Color.rgb(150, 252, 150, 0.9);
    private static final Color COLOR_ERROR_DIALOG = Color.PINK;

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setUpComponents(String text, Image img, Color backgroundColor) {
        setUpText(text);
        setUpAvatar(img);
        setUpBackground(backgroundColor);
    }

    private void setUpText(String text) {
        dialog.setText(text);
    }

    private void setUpAvatar(Image img) {
        displayPicture.setImage(img);
    }

    private void setUpBackground(Color backgroundColor) {
        CornerRadii cornerRadii = new CornerRadii(5);
        Insets insets = new Insets(-4);
        BackgroundFill backgroundFill = new BackgroundFill(backgroundColor, cornerRadii, insets);
        Background background = new Background(backgroundFill);
        dialog.setBackground(background);
    }

    /**
     * Creates a user dialogue box with the given user message text and the avatar icon.
     *
     * @param text Text message.
     * @param img  Avatar icon image.
     * @return {@code DialogBox} representing a user command.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox();
        db.setUpComponents(text, img, COLOR_USER_DIALOG);
        return db;
    }

    /**
     * Creates a dialogue box with the given message text, the avatar icon,
     * and sets the background color according to whether the message is an error message.
     *
     * @param text       Text message.
     * @param img        Avatar icon image.
     * @param isErrorMsg Boolean indicating whether the message text is an error message.
     * @return {@code DialogBox} representing a reply.
     */
    public static DialogBox getPopoDialog(String text, Image img, boolean isErrorMsg) {
        var db = new DialogBox();
        if (isErrorMsg) {
            db.setUpComponents(text, img, COLOR_ERROR_DIALOG);
        } else {
            db.setUpComponents(text, img, COLOR_POPO_DIALOG);
        }
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
