
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

    private DialogBox(String text, Image img,Color color) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setClip(new Circle(50, 50, 50));
        displayPicture.setImage(img);
        this.setBackground(new Background(new BackgroundFill(color, null, null)));
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

    public static DialogBox getUserDialog(String text, Image img, Color color) {
        var userDialog = new DialogBox(text, img, color);
        userDialog.dialog.setStyle("-fx-background-color: rgb(0, 191, 255); " + "-fx-background-radius: 5;"
                + "-fx-padding: 0;" + "-fx-border-color: rgb(237, 237, 119);" + "-fx-border-radius: 5;");
        return userDialog;
    }

    public static DialogBox getDukeDialog(String text, Image img,Color color) {
        var dukeDialog = new DialogBox(text, img, color);
        dukeDialog.dialog.setStyle("-fx-background-color: rgb(179, 153, 255); " + "-fx-background-radius: 5;"
                + "-fx-padding: 0;" + "-fx-border-color: rgb(235, 188, 218);" + "-fx-border-radius: 5;");
        dukeDialog.flip();
        return dukeDialog;
    }
}
