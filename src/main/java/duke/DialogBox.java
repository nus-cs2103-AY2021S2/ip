package duke;

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

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    //Solution adapted from https://github.com/TeoHoeKeat/ip/blob/master/src/main/java/fakebot/ui/DialogBox.java
    private void style() {
        CornerRadii rad = new CornerRadii(5);
        Color color = Color.rgb(98,  125,  168);
        BackgroundFill backgroundFill = new BackgroundFill(color, rad, new Insets(-2, -2, -2 , -2));
        Background background = new Background(backgroundFill);
        text.setBackground(background);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        DialogBox result = new DialogBox(l, iv);
        result.style();
        return result;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        DialogBox result = new DialogBox(l, iv);
        result.style();
        result.flip();
        return result;
    }
}
