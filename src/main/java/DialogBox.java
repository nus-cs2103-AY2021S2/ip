import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class DialogBox extends HBox {

    /**
     * Represents the output dialog box.
     * @param l The text to be displayed.
     * @param c Circle with the image to be displayed.
     */
    public DialogBox(Label l, Circle c, String who) {
        Rectangle r = new Rectangle(200, 200, 500, 80);
        r.setFill(Color.POWDERBLUE);
        r.setArcWidth(30.0);
        r.setArcHeight(20.0);

        StackPane sp = new StackPane(r, l);
        HBox hb;
        if (who.equals("d")) {
            if (l.getText().equals(Ui.invalidKeywordExceptionMessage())) {
                r.setFill(Color.YELLOW);
            }

            if (l.getText().contains(Ui.invalidTaskFormatBasicExceptionMessage())) {
                r.setFill(Color.RED);
            }
            hb = new HBox(c, sp);
        } else {
            hb = new HBox(sp, c);
        }
        hb.setPadding(new Insets(15, 0, 15, 0));
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(hb);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, Circle c) {
        //l.setPadding(new Insets(30, 20, 30, 20));
        return new DialogBox(l, c, "u");
    }

    public static DialogBox getDukeDialog(Label l, Circle c) {
        //l.setPadding(new Insets(30, 20, 30, 20));
        var db = new DialogBox(l, c, "d");
        db.flip();
        return db;
    }
}