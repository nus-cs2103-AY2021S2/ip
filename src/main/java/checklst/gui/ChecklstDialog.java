package checklst.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class ChecklstDialog extends DialogBox {

    public ChecklstDialog(Label l, ImageView iv) {
        // Should use a static Label here but for some reason static variables don't work with JavaFx.
        super(new Label("Checklst"), l, iv);

        Color dialogColor = new Color(0, 0.7, 1, 1);
        this.text.setBackground(
            new Background(new BackgroundFill(dialogColor, new CornerRadii(7.0), Insets.EMPTY)));
    
        this.flip();
    }

}
