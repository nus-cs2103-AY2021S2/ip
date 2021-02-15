import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    /**
     * Represents the output dialog box.
     * @param l The text to be displayed.
     * @param iv The image to be displayed.
     */
    public DialogBox(Label l, ImageView iv) {
        l.setWrapText(true);
        iv.setFitWidth(100.0);
        iv.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, iv);
    }

    public DialogBox(Label l, Circle c) {
        l.setWrapText(true);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, c);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getUserDialog(Label l, Circle c) {
        return new DialogBox(l, c);
    }

    public static DialogBox getDukeDialog(Label l, Circle c) {
        var db = new DialogBox(l, c);
        db.flip();
        return db;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}