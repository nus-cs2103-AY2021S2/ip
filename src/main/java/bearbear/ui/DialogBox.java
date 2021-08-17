package bearbear.ui;

import java.io.IOException;
import java.util.Collections;

import bearbear.exceptions.UiException;
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
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) throws UiException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new UiException("Failure in creating DialogBox");
        }

        dialog.setText(text);
        dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(6),
                new Insets(-3, -3, -3, -3))));
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
     * Creates a user dialogue box with a user message label and an avatar icon.
     * @param text User message as text.
     * @param img Avatar icon image.
     * @return {@code DialogBox} object representing a user command.
     * @throws UiException If error occurs when creating user DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img) throws UiException {
        return new DialogBox(text, img);
    }


    /**
     * Creates a bearBear dialogue box with a bearBear message label and an avatar icon.
     * @param text A {@code BearBear} chat bot message as Label.
     * @param img Avatar icon image.
     * @return {@code DialogBox} object representing a chat bot response.
     * @throws UiException If error occurs when creating BearBear DialogBox.
     */
    public static DialogBox getBearBearDialog(String text, Image img) throws UiException {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
