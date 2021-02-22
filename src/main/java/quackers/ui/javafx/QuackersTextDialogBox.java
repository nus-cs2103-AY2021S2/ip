package quackers.ui.javafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class QuackersTextDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private QuackersTextDialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/QuackersTextDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.loadProperties(text, image);
    }

    public static QuackersTextDialogBox getDialogBox(String text, Image img) {
        return new QuackersTextDialogBox(text, img);
    }

    private void loadProperties(String text, Image image) {
        this.setDisplayPicture(image);
        this.setText(text);
    }

    private void setDisplayPicture(Image image) {
        this.displayPicture.setImage(image);
    }

    private void setText(String text) {
        this.dialog.setText(text);
    }
}
