package chat;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Class for a custom made user dialog box.
 * UserDialogBox class includes user's command to Chat the Cat.
 */

public class UserDialogBox extends HBox {

    @FXML
    private Label dialog;

    /**
     * Initialises Chat Dialog Box object.
     *
     * @param text Displayed text in dialog box.
     */
    private UserDialogBox(String text) {
        assert text != "";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
    }

    /**
     * Returns a dialog box with user input.
     *
     * @param text User's inputted text.
     * @return DialogBox object.
     */
    public static UserDialogBox getUserDialog(String text) {
        assert text != "";

        return new UserDialogBox(text);
    }

}
