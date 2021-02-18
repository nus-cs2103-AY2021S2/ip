package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * A DialogBox handles the DialogBox in the GUI for both user and Duke
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Handles DialogBox in the GUI for both the user and Duke
     * @param l label of the DialogBox
     * @param iv Image of the user/Duke
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        Circle circle = new Circle();
        circle.setRadius(50.0);
        circle.setCenterX(50.0);
        circle.setCenterY(50.0);

        displayPicture.setClip(circle);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setSpacing(10.0);
    }

    /**
     * Flips the dialog box
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Makes a DialogBox for the user
     * @param l label of the DialogBox
     * @param iv image of the user
     * @return a DialogBox for the user
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Makes a DialogBox for Duke
     * @param l label of the DialogBox
     * @param iv image of Duke
     * @return a DialogBox for Duke
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
