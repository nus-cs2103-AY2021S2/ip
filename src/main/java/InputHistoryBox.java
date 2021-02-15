import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class InputHistoryBox extends HBox {

    private static final Background BG_LIGHT = new Background(new BackgroundFill(
            Color.valueOf("#e0e0e0"), new CornerRadii(20), Insets.EMPTY));
    private static final Background BG_DARK = new Background(new BackgroundFill(
            Color.valueOf("#d9d9d9"), new CornerRadii(20), Insets.EMPTY));
    private static final Border BORDER_TRANS = new Border(new BorderStroke(
            Color.valueOf("#f4f4f4"),
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(2, 4, 2, 4)
    ));

    private static int numBoxes = 0;

    @FXML
    private Label dialog;

    private InputHistoryBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/InputHistoryBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        setAlternatingBgColor();
        this.setBorder(BORDER_TRANS);
        numBoxes++;
    }

    public static InputHistoryBox getBox(String text) {
        return new InputHistoryBox(text);
    }

    private void setAlternatingBgColor() {
        if (numBoxes % 2 == 0) {
            this.setBackground(BG_DARK);
        } else {
            this.setBackground(BG_LIGHT);
        }
    }
}
