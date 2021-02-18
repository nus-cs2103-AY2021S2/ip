package nodes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class DialogBox extends HBox {
    private final Label text;
    private final ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setFont(Font.font ("Consolas"));
        Circle clip = new Circle( 50, 50,50);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        displayPicture.setClip(clip);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.setPadding(new Insets(5, 10, 5, 10));
        db.setSpacing(10);
        db.setAlignment(Pos.CENTER_RIGHT);
        db.text.setFont(Font.font("Consolas", FontWeight.BOLD,
                FontPosture.REGULAR, 12));
        db.text.setTextFill(Color.SADDLEBROWN);
        return db;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.setPadding(new Insets(5, 10, 5, 10));
        db.setSpacing(10);
        db.text.setTextFill(Color.DARKSLATEBLUE);
        db.flip();
        return db;
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}