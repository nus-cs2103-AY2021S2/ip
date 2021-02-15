import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class DialogBox extends HBox {

    /**
     * Represents the output dialog box.
     * @param l The text to be displayed.
     * @param c Circle with the image to be displayed.
     */
    public DialogBox(Label l, Circle c) {
        //l.setStyle("-fx-background-color:rgba(255, 105, 180, 0.7)");
        Rectangle r = new Rectangle(200, 200, 500, 80);
        r.setFill(Color.ALICEBLUE);
        r.setArcWidth(30.0);
        r.setArcHeight(20.0);

        l.setWrapText(true);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(r, c);
        //this.getChildren().addAll(l, c);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, Circle c) {
        return new DialogBox(l, c);
    }

    public static DialogBox getDukeDialog(Label l, Circle c) {
        var db = new DialogBox(l, c);
        db.flip();
        return db;
    }
}