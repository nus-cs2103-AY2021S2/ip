package checklst.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class UserDialog extends DialogBox {
    
    private final static Label NAME_LABEL = new Label("You");

    public UserDialog(Label l, ImageView iv) {
        super(NAME_LABEL, l, iv);

        Color dialogColor = new Color(1, 0.7, 0, 1);
        this.text.setBackground(
            new Background(new BackgroundFill(dialogColor, new CornerRadii(7.0), Insets.EMPTY)));
    }

}
