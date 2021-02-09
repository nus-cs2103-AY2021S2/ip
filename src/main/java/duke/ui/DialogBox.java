package duke.ui;

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
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private HBox hbox;
    @FXML
    private HBox dialogContainer;
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

        dialog.setText(text);
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
     * Sets the alignment of the Dialog container.
     *
     * @param isUser a boolean representing if the dialog box if of the user.
     */
    private void setDialogContainerDirection(boolean isUser) {
        if (isUser) {
            dialogContainer.setAlignment(Pos.CENTER_RIGHT);
        } else {
            dialogContainer.setAlignment(Pos.CENTER_LEFT);
        }
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userBox = new DialogBox(text, img);
        userBox.setDialogContainerDirection(true);
        return userBox;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox dukeBox = new DialogBox(text, img);
        dukeBox.flip();
        dukeBox.setDialogContainerDirection(false);
        return dukeBox;
    }
}