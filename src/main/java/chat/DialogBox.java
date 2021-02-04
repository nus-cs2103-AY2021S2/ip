package chat;

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

/**
 * Class for a custom made dialog box.
 * DialogBox class includes both user dialog and chat dialog box.
 */

public class DialogBox extends HBox {
    
    @FXML
    private Label dialog;
    
    @FXML
    private ImageView displayPicture;

    /**
     * Initialises Dialog Box object.
     * 
     * @param text Displayed text in dialog box.
     * @param img Displayed image in dialog box.
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

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips dialog box horizontally.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a dialog box with user input.
     * 
     * @param text User's inputted text.
     * @param img Users' image.
     * @return DialogBox object.
     */
    public static DialogBox getUserDialog(String text, Image img) { 
        return new DialogBox(text, img);
    }

    /**
     * Returns a dialog box with chat's message or response.
     * 
     * @param text Chat's message.
     * @param img Chat's image.
     * @return DialogBox object.
     */
    public static DialogBox getChatDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip(); 
        return db;
    }
}
