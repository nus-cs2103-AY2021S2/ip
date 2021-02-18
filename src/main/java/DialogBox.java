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


public class DialogBox extends HBox {

    private Label text;

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        customiseBox();
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Customise dialog box in terms of colour and spacing
     * For a more aesthetic looking dialog boc
     */
    private void customiseBox() {
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5), null)));
        this.setPadding(new Insets(15, 12, 15, 12));
        this.setSpacing(10);
    }

    private static void customiseUserBox(DialogBox userDialogBox) {
        userDialogBox.setBackground(new Background(new BackgroundFill(Color.web("#eaffd0"), new CornerRadii(5), null)));
        userDialogBox.setPadding(new Insets(15, 12, 15, 12));
        userDialogBox.setSpacing(10);
    }

    private static void customiseWormBox(DialogBox wormDialogBox) {
        wormDialogBox.setBackground(new Background(new BackgroundFill(Color.web("#ffb4b4"), new CornerRadii(5), null)));
        wormDialogBox.setPadding(new Insets(15, 12, 15, 12));
        wormDialogBox.setSpacing(10);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userBox = new DialogBox(text, img);
        customiseUserBox(userBox);
        return userBox;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        customiseWormBox(db);
        return db;
    }
}
