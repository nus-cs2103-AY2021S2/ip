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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

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

    /**
     * Constructs a DialogBox object for the program's responses
     *
     * @param dukeText response based on given user input
     * @param dukeImg image representing duke
     */
    private DialogBox(String dukeText, Image dukeImg) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DukeDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(dukeText);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        displayPicture.setImage(dukeImg);
    }

    /**
     * Constructs a DialogBox object for user inputs
     *
     * @param userImg image representing the user
     * @param userText text input by the user
     */
    private DialogBox(Image userImg, String userText) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(userText);
        displayPicture.setImage(userImg);
    }

    /**
     * Returns a DialogBox object containing the responses of the duke program.
     *
     * @param text the response to output based on commands given
     * @param img the image representing Duke
     * @return a dialog box with the response based on user input
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        return new DialogBox(text, img);
    }


    /**
     * Returns a DialogBox object containing the inputs of the user.
     *
     * @param text the response to output based on commands given
     * @param img the image representing the user
     * @return a dialog box with the user input
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(img, text);
    }
}