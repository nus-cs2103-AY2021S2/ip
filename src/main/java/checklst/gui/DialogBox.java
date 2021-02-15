package checklst.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    private DialogBox(Label l, ImageView iv) {
        this.text = l;
        this.text.setFont(new Font("Arial", 14));
        this.displayPicture = iv;

        this.text.setWrapText(true);
        this.text.setPadding(new Insets(10));
        this.displayPicture.setFitWidth(50.0);
        this.displayPicture.setFitHeight(50.0);
        DialogBox.setMargin(this.displayPicture, new Insets(3));

        this.setPadding(new Insets(5));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.setBackground(
            new Background(new BackgroundFill(new Color(1, 0.7, 0, 1), new CornerRadii(7.0), Insets.EMPTY)));

        return db;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        db.setBackground(
            new Background(new BackgroundFill(new Color(0, 0.7, 1, 1), new CornerRadii(7.0), Insets.EMPTY)));

        return db;
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
