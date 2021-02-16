package duke.controller;

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
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            assert fxmlLoader != null : "FXML Loader is not initialised";
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setPadding(new Insets(0, 5, 0, 5));
        dialog.setText(text);
        displayPicture.setImage(img);
        Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);
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
     * Creates a user dialog box
     *
     * @param text Input from user
     * @param img User display picture
     * @return DialogBox with User input and display picture
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        assert db != null : "Dialog Box is not initialised";
        db.setBackground(new Background(new BackgroundFill(Color.BEIGE,
                new CornerRadii(5.0),
                Insets.EMPTY)));
        return db;
    }

    /**
     * Creates a duke dialog box
     *
     * @param text Response from duke
     * @param img Duke display picture
     * @return DialogBox with Duke input and display picture
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        assert db != null : "Dialog Box is not initialised";
        db.flip();
        db.setBackground(new Background(new BackgroundFill(Color.CRIMSON,
                new CornerRadii(5.0),
                Insets.EMPTY)));
        return db;
    }
}
