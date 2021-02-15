package checklst.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ErrorDialog extends DialogBox {

    public ErrorDialog(Label l, ImageView iv) {
        // Should use a static Label here but for some reason static variables don't work with JavaFx.
        super(new Label("Checklst - Error"), l, iv);

        Color dialogColor = new Color(1, 0, 0, 1);
        this.text.setBackground(
            new Background(new BackgroundFill(dialogColor, new CornerRadii(7.0), Insets.EMPTY)));
    
        this.flip();
    }

}
