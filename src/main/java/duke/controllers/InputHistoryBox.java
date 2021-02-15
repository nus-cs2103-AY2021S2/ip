package duke.controllers;

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
import javafx.scene.paint.Color;

/**
 * Represents a single entry of past user inputs, displayed in associated container.
 */
public class InputHistoryBox extends HBox {

    private static final CornerRadii CORNER_DEFAULT = new CornerRadii(20);
    private static final Background BG_FADED = new Background(new BackgroundFill(
            Color.valueOf("#e9e9e9"), CORNER_DEFAULT, Insets.EMPTY));
    private static final Background BG_OK = new Background(new BackgroundFill(
            Color.valueOf("#e1f1e1"), CORNER_DEFAULT, Insets.EMPTY));
    private static final Background BG_BAD = new Background(new BackgroundFill(
            Color.valueOf("#f1e1e1"), CORNER_DEFAULT, Insets.EMPTY));
    private static final Border BORDER_TRANSPARENT = new Border(new BorderStroke(
            Color.valueOf("#f4f4f4"),
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY,
            new BorderWidths(2, 4, 2, 4)
    ));
    private static final Color TEXT_BAD = Color.valueOf("#bbbbbb");

    @FXML
    private Label dialog;

    private final boolean isBadInput;

    /**
     * Default constructor for inputHistoryBox.
     *
     * @param text Text to be displayed
     * @param isBadInput Tweaks final formatting of text box
     */
    public InputHistoryBox(String text, boolean isBadInput) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/InputHistoryBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.isBadInput = isBadInput;

        /* Update text and set display formatting */
        dialog.setText(text);
        setBorder(BORDER_TRANSPARENT);
        setBackground((isBadInput) ? BG_BAD : BG_OK);
    }

    /**
     * Changes color of font to a faded version.
     *
     * Visual indication of historical user input.
     */
    public void setInputFaded() {
        setBackground(BG_FADED);
        if (isBadInput) {
            dialog.setTextFill(TEXT_BAD);
        }
    }
}
