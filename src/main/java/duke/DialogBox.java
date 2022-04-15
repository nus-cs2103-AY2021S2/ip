package duke;

import java.io.IOException;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Encompasses a dialog, part of a conversation.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a DialogBox controller using FXML.
     *
     * @param text Text to be put in the label node.
     */
    private DialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setPadding(new Insets(10, 15, 10, 15));
    }

    /**
     * Creates a DialogBox specific to Duke's messages.
     *
     * @param text  Text to be put in the label node.
     * @param image Image to be put into the ImageView node.
     * @return Duke's DialogBox.
     */
    public static DialogBox getDukeDialog(String text, Image image) {
        var dukeDialogBox = new DialogBox(text);
        dukeDialogBox.dialog.setBackground(new Background(new BackgroundFill(
                Color.rgb(255, 255, 255),
                new CornerRadii(10.0),
                new Insets(0, 5, 0, 5))));

        ImageView imageNode = new ImageView(image);
        imageNode.setFitWidth(50.0);
        imageNode.setFitHeight(50.0);

        dukeDialogBox.getChildren().add(imageNode);
        dukeDialogBox.flip();
        return dukeDialogBox;
    }

    /**
     * Creates a DialogBox specific to the user's messages.
     *
     * @param text Text to be put in the Label node.
     * @return User's DialogBox.
     */
    public static DialogBox getUserDialog(String text) {
        var userDialogBox = new DialogBox(text);
        userDialogBox.dialog.setBackground(new Background(new BackgroundFill(
                Color.rgb(102, 255, 204),
                new CornerRadii(10.0),
                null)));
        return userDialogBox;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
