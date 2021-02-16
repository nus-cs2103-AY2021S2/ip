package duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final String DUKE_DIALOG_BOX = "/view/DialogBox.fxml";
    private static final String USER_DIALOG_BOX = "/view/UserDialogBox.fxml";

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, boolean isUser) {
        try {
            String resourcePath = isUser ? USER_DIALOG_BOX : DUKE_DIALOG_BOX;
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(resourcePath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
    }

    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, true);
    }

    public static DialogBox getDukeDialog(String text) {
        return new DialogBox(text, false);
    }

}
