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

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent
 * the speaker's face and a label containing text from the speaker.
 */
public class StartingBox extends HBox {
    @FXML
    private Label welcomeMessage;
    @FXML
    private ImageView logoPhoto;
    @FXML
    private ImageView dukePhoto;

    /**
     * Generates the starting box of the CLI app
     *
     * @param text String to be displayed when the app starts
     * @param img The image displayed as the Logo when the app starts
     * @param dukeImg The image of Duke when it starts
     */
    private StartingBox(String text, Image img, Image dukeImg) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/StartingBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        welcomeMessage.setText(text);
        logoPhoto.setImage(img);
        dukePhoto.setImage(dukeImg);
    }


    public static StartingBox getStartMessage(String text, Image img, Image dukeImg) {
        return new StartingBox(text, img, dukeImg);
    }


}