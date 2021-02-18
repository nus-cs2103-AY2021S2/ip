package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a Label
 * containing text from the speaker. The variables of this Object is specified in the .fxml file in the resource folder.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor
     * @param text Text to display on screen.
     * @param img Image to display on screen, user currently manually sets the image in MainWindow.java.
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
        BackgroundFill bgf = new BackgroundFill(
                Color.CORNSILK,
                new CornerRadii(10),
                null
        );
        final Circle clip = new Circle(50, 50, 50);

        dialog.setText(text);
        dialog.setBackground(new Background(bgf));
        displayPicture.setClip(clip);
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
     * Show user side input (right side of window).
     * @param text Text to display on screen.
     * @param img User image to display on screen, user currently manually sets the image in MainWindow.java.
     * @return
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Show duke side output (left side of window).
     * @param text Text to display on screen.
     * @param img Duke image to display on screen, user currently manually sets the image in MainWindow.java.
     * @return
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
