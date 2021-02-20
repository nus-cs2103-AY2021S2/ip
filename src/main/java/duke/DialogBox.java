package duke;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

    private static Image userBoxImage = new Image(MainWindow.class.getResourceAsStream("/images/UserBoxImage.png"));
    private static Image dukeBoxImage = new Image(MainWindow.class.getResourceAsStream("/images/DukeBoxImage.png"));

    private DialogBox(String text, Image img, Color color) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setTextFill(color);
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50, 50, 50));
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
     * Creates a DialogBox representing the user's input into the program.
     * @param text the user's input into the program
     * @param img the Image that represents the user
     * @return DialogBox
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialog = new DialogBox(text, img, Color.BLACK);
        // @@author CSmortal-reused
        // Reused from https://github.com/nus-cs2103-AY2021S1/ip/pull/64/commits/1e8c598c8119725587d1e2c84ae89434249120ae
        BackgroundImage backgroundImage = new BackgroundImage(userBoxImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        userDialog.setBackground(background);
        return userDialog;
        // @@author
    }

    /**
     * Creates a DialogBox representing the program's response to the user
     * @param text the program's response to the user's input
     * @param img the Image that represents the program Duke
     * @return DialogBox
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img, Color.RED);
        // @@author CSmortal-reused
        // Reused from https://github.com/nus-cs2103-AY2021S1/ip/pull/64/commits/1e8c598c8119725587d1e2c84ae89434249120ae
        BackgroundImage backgroundImage = new BackgroundImage(dukeBoxImage,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        db.setBackground(background);
        // @@author
        db.flip();
        return db;
    }
}
