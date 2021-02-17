package duke.ui;

import java.io.IOException;

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



public class DialogBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for a new dialog box.
     */
    public DialogBox(String s, Image iv) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/style/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        text.setText(s);
        displayPicture.setImage(iv);

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

    /**
     * Get the user dialog box in correct format.
     */
    public static DialogBox getUserDialog(String l, Image iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Get Duke dialog box in correct format.
     */
    public static DialogBox getDukeDialog(String l, Image iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

}
