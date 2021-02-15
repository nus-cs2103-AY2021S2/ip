package lihua.ui;

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
 */
//@@author Cheng20010201
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications.
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, Background bg) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setBackground(bg);
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
     * Gets a dialog box object containing the bot response.
     *
     * @param text Bot response.
     * @param img Bot img.
     * @return A new DialogBox containing the input information.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dBox = new DialogBox(text, img, getUserBackground());
        return dBox;
    }

    /**
     * Gets a flipped dialog box object containing the human message.
     *
     * @param text Human input.
     * @param img Human message content.
     * @return A new DialogBox containing the input information.
     */
    public static DialogBox getLihuaDialog(String text, Image img) {
        DialogBox dBox = new DialogBox(text, img, getLihuaBackground());
        dBox.flip();
        return dBox;
    }

    //@@author
    private static Background getUserBackground() {
        BackgroundFill background_fill = new BackgroundFill(Color.LIGHTPINK,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        return background;
    }

    private static Background getLihuaBackground() {
        BackgroundFill background_fill = new BackgroundFill(Color.LIGHTSKYBLUE,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        return background;
    }
}
