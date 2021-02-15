import java.awt.*;
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

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setDesign();
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();

        // if invalid command, set background to pink.
        if (Parser.warning) {
            db.setWarning();
        } else {
            db.setReplyDesign();
        }

        return db;
    }

    private void setWelcomeSettings() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        this.setStyle("-fx-background-color: #EEC5A9;"
                + "-fx-background-radius: 10.0");
        dialog.setStyle("-fx-font-size: 16;"
                + "-fx-text-fill: white;"
                + "-fx-alignment: baseline-right;");
    }

    public static DialogBox welcomeMessage(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setWelcomeSettings();
        return db;
    }

    private void setDesign() {
        this.dialog.setStyle("-fx-background-color: #121212;"
                + "-fx-text-fill: white;"
                + "-fx-min-width: 150px;"
                + "-fx-font-size: 14;"
                + "-fx-alignment: baseline-center;"
                + "-fx-border-width: 10;"
                + "-fx-background-radius: 15.0;"
                + "-fx-padding: 10.0");
    }

    private void setWarning() {
        this.dialog.setStyle("-fx-background-color: hotpink;"
                + "-fx-text-fill: white;"
                + "-fx-min-width: 150px;"
                + "-fx-font-size: 14;"
                + "-fx-alignment: baseline-center;"
                + "-fx-border-width: 10;"
                + "-fx-background-radius: 15.0;"
                + "-fx-padding: 10.0");
        Parser.warning = false;
    }

    private void setReplyDesign() {
        this.dialog.setStyle("-fx-background-color: #696969;"
                + "-fx-text-fill: white;"
                + "-fx-min-width: 150px;"
                + "-fx-font-size: 14;"
                + "-fx-alignment: baseline-center;"
                + "-fx-border-width: 10;"
                + "-fx-background-radius: 15.0;"
                + "-fx-padding: 10.0");
    }
}