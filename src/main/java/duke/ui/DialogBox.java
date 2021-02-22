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
 * This class controls the creation and styling of dialog boxes for both the user and Duke.
 * A dialog box consists of a <code>Label</code> containing a user input or a response from Duke, and an
 * <code>ImageView</code> to represent the user's or Duke's avatar respectively.
 */
public class DialogBox extends HBox {
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

        this.dialog.setText(text);
        this.displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the display picture is on the left and the dialog box
     * is on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a styled dialog box to display the users' input.
     *
     * @param text A string of user input.
     * @param img  An <code>Image</code> to use as the users' avatar.
     * @return A <code>DialogBox</code> to be displayed.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        String lightBlue = "#d4ebf2";
        db.styleLabelBox(lightBlue);
        return db;
    }

    /**
     * Creates a styled dialog box to display Duke's response (to valid input).
     *
     * @param text Duke's response.
     * @param img  An <code>Image</code> to use as the Duke's avatar.
     * @return A <code>DialogBox</code> to be displayed.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        String lightGrey = "#f0f0f0";
        db.styleLabelBox(lightGrey);
        db.flip();
        db.setMinHeight(db.getValidDialogHeight(text));
        return db;
    }

    /**
     * Creates a styled dialog box to display Duke's response (to an invalid input).
     *
     * @param text Duke's response.
     * @param img  An <code>Image</code> to use as the Duke's avatar.
     * @return A <code>DialogBox</code> to be displayed.
     */
    public static DialogBox getDukeDialogForInvalid(String text, Image img) {
        var db = new DialogBox(text, img);
        String lightRed = "#ffe5e5";
        db.styleLabelBox(lightRed);
        db.flip();
        return db;
    }

    /**
     * Styles the label boxes in which the dialogues will be shown.
     *
     * @param color HEX code of desired background color for label box.
     */
    private void styleLabelBox(String color) {
        this.dialog.setStyle("-fx-background-color: " + color + ";");
    }

    /**
     * Dynamically set the height for Duke's dialogue boxes based on the text to be displayed.
     * This method is for displaying responses to valid input.
     *
     * @param text Text to be displayed.
     * @return Height to use for the dialogue box
     */
    private int getValidDialogHeight(String text) {
        int numLines = text.split("\n", -1).length;
        return 60 + 17 * numLines;
    }
}
