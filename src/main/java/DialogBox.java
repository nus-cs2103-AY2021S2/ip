import javafx.beans.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.Node;


public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        text.setWrapText(true);
        displayPicture.setFitHeight(100.0);
        displayPicture.setFitWidth(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        //order that you put in the children matters
    }

    public static DialogBox getDialog(Label l, ImageView iv, boolean isUser) {
        if (isUser) {
            return new DialogBox(l, iv);
        } else {
            DialogBox db = new DialogBox(l, iv);
            db.flip();
            return db;
        }
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
