package checklst.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ErrorDialog extends DialogBox {

    private static final String NAME = "Checklst - Error";

    /**
     * Creates an error dialog from the Checklst bot.
     * @param l Error to be shown.
     * @param iv Image of Checklst.
     */
    public ErrorDialog(Label l, ImageView iv) {
        super(NAME, l, iv);

        Color dialogColor = new Color(1, 0, 0, 1);
        this.text.setBackground(
            new Background(new BackgroundFill(dialogColor, new CornerRadii(7.0), Insets.EMPTY)));

        this.flip();
    }

}
