package dukegui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label content;
    private ImageView display;

    public DialogBox(Label text, ImageView picture, boolean reverse) {
        content = text;
        display = picture;

        content.setWrapText(true);
        display.setFitWidth(100.0);
        display.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(content, display);

        if (reverse) {
            this.setAlignment(Pos.TOP_LEFT);
            ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
            FXCollections.reverse(tmp);
            this.getChildren().setAll(tmp);
        }
    }

    // default constructor in user perspective
    public DialogBox(Label text, ImageView picture) {
        this(text, picture, false);
    }
}
