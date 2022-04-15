package duke.uielement;

import java.io.IOException;
import java.util.Collections;

import duke.Ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final ColorPicker ERROR_COLOR_PICKER = new ColorPicker(Color.RED);
    @FXML
    private Label dialog;
    @FXML
    private Text name;

    private DialogBox(String text, String nameInput) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("DialogBox.fxml"));
            assert fxmlLoader != null : "FXML file not loaded. Location specified might be wrong";
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.autosize();
        name.setText(nameInput);
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
     * Creates the dialogue box object for the user input.
     * @param text text the User has inputted
     * @return the DialogBox representation of the User input.
     */
    public static DialogBox getUserDialog(String text) {
        DialogBox db = new DialogBox(text, "User");
        return db;
    }

    /**
     * Creates the dialogue box object for Duke output.
     * @param text String representation of Duke output.
     * @return the DialogBox representation of Duke output.
     */
    public static DialogBox getDukeDialog(String text) {
        DialogBox db = new DialogBox(text, "Duke");
        db.flip();
        return db;
    }

    /**
     * Creates the dialogue box object for Error output.
     * @param text String representation of Error output.
     * @return the DialogBox representation of Error output.
     */
    public static DialogBox getErrorDialog(String text) {
        DialogBox db = DialogBox.getDukeDialog(text);
        db.dialog.textFillProperty().bind(ERROR_COLOR_PICKER.valueProperty());
        return db;
    }

}
