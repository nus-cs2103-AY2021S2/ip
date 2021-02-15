package duke.ui;

import java.io.IOException;

import duke.response.ResponseStatus;
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

        Circle clip = new Circle(displayPicture.getFitHeight() / 2,
                displayPicture.getFitHeight() / 2, displayPicture.getFitHeight() / 2);
        displayPicture.setClip(clip);
        displayPicture.setImage(img);

        setHeight(dialog.getHeight());

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        this.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #6A82FB, #FC5C7D); -fx-background-radius: 20px;");
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img, ResponseStatus status) {
        var db = new DialogBox(text, img);
        db.flip();
        if (status == ResponseStatus.ERROR) {
            db.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #833ab4, #fd1d1d); -fx-background-radius: 20px;");
        }
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        return DialogBox.getDukeDialog(text, img, ResponseStatus.OK);
    }
}
