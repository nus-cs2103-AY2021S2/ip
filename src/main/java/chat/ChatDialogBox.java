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
 * Class for a custom made Chat dialog box.
 * ChatDialogBox class includes an image of Chat and Chat's message to user.
 */

public class ChatDialogBox extends HBox {
    
    @FXML
    private Label dialog;
    
    @FXML
    private ImageView displayPicture;

    /**
     * Initialises Chat Dialog Box object.
     * 
     * @param text Displayed text in dialog box.
     * @param img Displayed image in dialog box.
     */
    private ChatDialogBox(String text, Image img) {
        assert text != ""; 
        assert img != null;
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ChatDialogBox.fxml"));
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
     * Returns a dialog box with chat's message.
     *
     * @param text Chat's message.
     * @param img Chat's image.
     * @return DialogBox object.
     */
    public static ChatDialogBox getChatDialog(String text, Image img) {
        assert text != "";
        assert img != null;
        
        return new ChatDialogBox(text, img);
    }
    
}
