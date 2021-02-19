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
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;
/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */

//@@ jellymias-reused
// Reused from http://https://github.com/jellymias/ip/blob/master/src/main/java/duke/DialogBox.java
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;


    /**
     * Constructor for dialoguebox that has an image(jpeg/gif) as input
     * @param text
     * @param img
     */
    //@@ banchiang-reused
    //Reused from https://github.com/banchiang/ip/blob/master/src/main/java/duke/DialogBox.java
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
    }
    //@@ banchiang

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
     * Gets the User dialogueBox
     * @param text
     * @param img
     * @return new DialogueBox for Users side
     */
    public static DialogBox getUserDialogue(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Gets Duke's dialogue box
     * @param text
     * @param img
     * @return new Dialogue box for Duke
     */
    public static DialogBox getDukeDialogue(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
//@@ jellymias