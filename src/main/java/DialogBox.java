import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, boolean isLuna) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        Circle circle = new Circle(50, 50, 40);
        displayPicture.setClip(circle);
        gui();
        if (isLuna) {
            lunaGui();
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.setUserAlignment();
        return db;
    }

    public static DialogBox getLunaDialog(String text, Image img) {
        var db = new DialogBox(text, img, true);
        db.flip();
        db.setLunaAlignment();
        return db;
    }

    /**
     * Sets the gui style for both the user and Luna.
     */
    public void gui() {
        dialog.setStyle("-fx-padding: 15.0;"
                + "-fx-border-width: 0");

        dialog.setBackground(new Background(new BackgroundFill(Color.POWDERBLUE, new CornerRadii(15.0),
                Insets.EMPTY)));
    }

    /**
     * Sets the gui style for error messages that Luna wants to display.
     */
    public void lunaGui() {
        if (dialog.getText().contains(Ui.invalidTaskFormatBasicExceptionMessage())
                || dialog.getText().contains(Ui.invalidKeywordExceptionMessage())
                || dialog.getText().contains(Ui.invalidNumberExceptionMessage())) {
            dialog.setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(15.0),
                    Insets.EMPTY)));
        }
    }

    /**
     * Aligns the user dialog box.
     */
    public void setUserAlignment() {
        this.setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Aligns Luna's dialog box.
     */
    public void setLunaAlignment() {
        this.setAlignment(Pos.TOP_LEFT);
    }
}
