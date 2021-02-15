package duke.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
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

        dialog.setText(text);
        dialog.setFont(new Font("Monospaced Regular", 14));
        dialog.setPadding(new Insets(10, 10, 10, 10));

        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50, 50, 45));
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

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        String lightBlue = "e8f4f8";
        db.styleLabelBox(lightBlue);
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        String lightGrey = "#f0f0f0";
        db.styleLabelBox(lightGrey);
        db.flip();
        return db;
    }

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
        this.dialog.setStyle("-fx-background-color: " + color + ";"
                + "-fx-border-radius: 10 10 10 10;"
                + "-fx-background-radius: 10 10 10 10;");
    }
}
