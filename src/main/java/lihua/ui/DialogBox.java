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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setupDisplayPicture(img);
        dialog.setText(text);
    }

    // @author Cheng20010201-reused
    // Code snippet modified from https://stackoverflow.com/questions/25622445/how-to-make-imageviews-rounded
    private void setupDisplayPicture(Image img) {
        // set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(
                displayPicture.getFitWidth(), displayPicture.getFitHeight()
        );
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        displayPicture.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = displayPicture.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        displayPicture.setClip(null);

        // apply a shadow effect.
        displayPicture.setEffect(new DropShadow(20, Color.YELLOWGREEN));
        displayPicture.setImage(img);
    }
    // @author

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
        DialogBox dBox = new DialogBox(text, img);
        dBox.setBackground(getUserBackground());
        return dBox;
    }

    /**
     * Gets a flipped dialog box object containing the human message.
     *
     * @param text Human input.
     * @param img Human message content.
     * @return A new DialogBox containing the input information.
     */
    public static DialogBox getLihuaDialogError(String text, Image img) {
        DialogBox dBox = new DialogBox(text, img);
        dBox.flip();
        dBox.setBackground(getLihuaBackgroundError());
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
        DialogBox dBox = new DialogBox(text, img);
        dBox.flip();
        dBox.setBackground(getLihuaBackground());
        return dBox;
    }

    //@@author
    private static Background getUserBackground() {
        BackgroundFill backgroundFill = new BackgroundFill(Color.SKYBLUE,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        return background;
    }

    private static Background getLihuaBackground() {
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTPINK,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        return background;
    }

    private static Background getLihuaBackgroundError() {
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTCORAL,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        return background;
    }
}
