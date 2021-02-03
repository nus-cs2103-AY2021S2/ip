package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialogue box with an avatar icon and the text message.
 */
public class DialogBox extends HBox {

    private DialogBox(Label l, ImageView imageView) {
        l.setWrapText(true);
        imageView.setFitHeight(100.0);
        imageView.setFitWidth(100.0);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, imageView);
        setSpacing(10.0);
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
     * Creates a user dialogue box with a user message label and an avatar icon.
     * @param label User message as Label.
     * @param imageView Avatar icon image.
     * @return {@code DialogBox} object representing a user command.
     */
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }

    /**
     * Creates a duke dialogue box with a duke message label and an avatar icon.
     * @param label Duke chat bot message as Label.
     * @param imageView Avatar icon image.
     * @return {@code DialogBox} object representing a chat bot response.
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageView) {
        var db = new DialogBox(label, imageView);
        db.flip();
        return db;
    }
}
