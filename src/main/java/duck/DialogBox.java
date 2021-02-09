package duck;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Create dialog box which include the Avatar and label
     *
     * @param l  the label including dialogue
     * @param iv the avatar
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        Label textSpace = new Label("   ");
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, textSpace, displayPicture);
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
     * create user's dialog box
     *
     * @param l  the label including dialogue
     * @param iv the avatar of user
     * @return dialog box
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * create Duke's dialog box
     *
     * @param l  the label including dialogue
     * @param iv the avatar of Duke
     * @return dialog box
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
