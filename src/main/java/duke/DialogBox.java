package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    public static final int CHAR_LINE_LIMIT = 36;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        // dialog.setMinHeight(Region.USE_PREF_SIZE);
        dialog.setText(text);
        dialog.setTextAlignment(TextAlignment.LEFT);

        displayPicture.setImage(img);
        final Circle clip = new Circle(30, 30, 30);
        displayPicture.setClip(clip);

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setBackground(new Background(new BackgroundFill(Color.web("#add8e6"), new CornerRadii(10.0),
                new Insets(0, 8, 0, 0))));
        db.dialog.setPadding(new Insets(10, 18, 10, 10));
        if (text.length() > CHAR_LINE_LIMIT) {
            db.dialog.setMaxWidth(280.0);
        }
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setBackground(new Background(new BackgroundFill(Color.web("#dcedc8"), new CornerRadii(10.0),
                new Insets(0, 0, 0, 8))));
        db.dialog.setPadding(new Insets(10, 10, 10, 18));
        db.dialog.setMaxWidth(290.0);
        return db;
    }
}
