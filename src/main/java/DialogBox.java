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
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
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
    private Circle displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //dont create new instance of dialog and picture
        dialog.setText(text);
        ImagePattern picture = new ImagePattern(img);
        displayPicture.setFill(picture);

        //this.getChildren().addAll(dialog, displayPicture);
        //no need to add the children, could be cause the .fxml adds in inside ready?
        //dialog.setMaxHeight(Double.MAX_VALUE);
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

    /*public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }*/

    public static DialogBox getDialog(String text, Image iv, boolean isUser) {
        DialogBox db = new DialogBox(text, iv);
        //padding btw dialog box and outer area
        db.setPadding(new Insets(5, 5, 5,5));
        //spacing btw the items in dialogbox
        db.setSpacing(5);
        if (isUser) {
            db.setStyle("-fx-background-color: #F0F0F0;");
            return db;
        } else {
            db.setStyle("-fx-background-color: #DCDCDC");
            db.flip();
            return db;
        }
    }
}