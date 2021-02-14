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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

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

    private DialogBox(String text, Image img, String role) {
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

        //@@author wongkokian-reused
        //Reused from https://github.com/jonfoocy/ip with minor modifications
        switch (role) {
        case "mando":
            dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(20.0),
                    new Insets(0))));
            break;
        case "yoda":
            dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(20.0),
                    new Insets(0))));
            break;
        case "error":
            dialog.setBackground(new Background(new BackgroundFill(Color.INDIANRED, new CornerRadii(20.0),
                    new Insets(0))));
            break;
        default:
        }
        //@@author
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, "mando");
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, "yoda");
        db.flip();
        return db;
    }

    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img, "error");
        db.flip();
        return db;
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
}
