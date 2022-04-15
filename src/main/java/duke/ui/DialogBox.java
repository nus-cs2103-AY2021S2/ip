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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**This control represents a dialog box consisting of an ImageView to represent the speaker's face, a label
 * containing the speaker's name and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private VBox vbox;
    @FXML
    private Label messageContent;
    @FXML
    private Text messageContent2;
    @FXML
    private Label senderName;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String name, String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        senderName.setText(name);
        messageContent2.setText(text);
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
        vbox.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns dialogue box containing user's dialogue.
     *
     * @param text  Dialogue text.
     * @param img   Image representing user.
     * @return Dialogue box containing user's dialogue.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox("user", text, img);
        db.flip();
        db.senderName.setId("userName");
        return db;
    }

    /**
     * Returns dialogue box containing bot's dialogue.
     *
     * @param text  Dialogue text.
     * @param img   Image representing bot.
     * @return Dialogue box containing bot's dialogue.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox("tabby", text, img);
        db.senderName.setId("tabbyName");
        db.messageContent2.setTextAlignment(TextAlignment.RIGHT);
        return db;
    }
}
