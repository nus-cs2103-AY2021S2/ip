import javafx.beans.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.shape.Circle;

import javafx.scene.Node;


public class DialogBox extends HBox {
    private Label text;
    //private ImageView displayPicture;
    private Circle circledPicture;

    public DialogBox(Label l, Image iv) {
        text = l;
        text.setFont(new Font("Arial", 15));
        //font type not changing
        ImagePattern displayPicture = new ImagePattern(iv);
        text.setWrapText(true);
        //displayPicture.setFitHeight(100.0);
        //displayPicture.setFitWidth(100.0);

        circledPicture = new Circle(50);
        circledPicture.setFill(displayPicture);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, circledPicture);
        //order that you put in the children matters
    }

    public static DialogBox getDialog(Label l, Image iv, boolean isUser) {
        DialogBox db = new DialogBox(l, iv);
        db.setPadding(new Insets(5, 5, 5,5));
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

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
