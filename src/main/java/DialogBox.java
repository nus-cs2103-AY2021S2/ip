import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class DialogBox extends HBox {

    private Label text;

    public DialogBox(Label l) {
        text = l;
        text.setFont(Font.font(
                "Arial",
                FontWeight.BLACK,
                FontPosture.REGULAR,
                24
        ));
        text.setWrapText(true);

        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().add(text);
    }
}
