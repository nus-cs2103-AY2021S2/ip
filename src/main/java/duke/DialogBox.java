package duke;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for dialog boxes.
 */
public class DialogBox extends HBox {
    private final Label label;
    private final boolean isDuke;
    private final boolean hasError;

    private DialogBox(Label label, boolean isDuke, boolean hasError) {
        assert label != null;
        assert !(!isDuke && hasError);

        this.label = label;
        this.isDuke = isDuke;
        this.hasError = hasError;

        this.setPadding(new Insets(10));
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().add(makeContent());
    }

    private VBox makeContent() {
        VBox container = new VBox(5);
        label.setWrapText(true);

        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Label timestampLabel = new Label(timestamp);
        timestampLabel.setFont(Font.font(10));
        timestampLabel.setTextFill(Color.gray(0.3));
        timestampLabel.setWrapText(true);
        container.setAlignment(Pos.TOP_RIGHT);

        container.getChildren().addAll(label, timestampLabel);

        Paint background;
        if(isDuke) {
            background = hasError ? Color.rgb(255, 100, 100) : Color.LIGHTBLUE;
        } else {
            background = Color.GAINSBORO;
        }

        container.setBackground(new Background(
                new BackgroundFill(background, new CornerRadii(5), Insets.EMPTY)));
        container.setPadding(new Insets(10));
        container.setMaxWidth(450);

        return container;
    }

    /**
     * Constructs a user's dialog box.
     * @param text Text to be inserted into the dialog.
     * @return A user's dialog box.
     */
    public static DialogBox userDialogMaker(String text) {
        Label label = new Label(text);
        return new DialogBox(label, false, false);
    }

    /**
     * Constructs a system's dialog box. The colour of the dialog box
     * will change depending if the dialog box shows an error message
     * or not.
     * @param text Text to be inserted into the dialog.
     * @param hasError Whether the system throws an error or not.
     * @return A system's dialog box.
     */
    public static DialogBox dukeDialogMaker(String text, boolean hasError) {
        Label label = new Label(text);
        DialogBox temp = new DialogBox(label, true, hasError);
        temp.setAlignment(Pos.TOP_LEFT);
        return temp;
    }
}
