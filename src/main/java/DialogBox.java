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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private HBox hbox;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,
                new CornerRadii(5, 5, 5, 5, false),
                new Insets(5, 5, 5, 50))));
        hbox.setPadding(new Insets(5,5,5,50));
        hbox.setSpacing(5);
        dialog.setText(text);
        dialog.setPadding(new Insets(15,50,15,15));
        displayPicture.setImage(img);

        Circle clip = new Circle(40);
        clip.setCenterX(displayPicture.getFitWidth() / 2);
        clip.setCenterY(displayPicture.getFitHeight() / 2);
        displayPicture.setClip(clip);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip(boolean isError) {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        if (isError) {
            hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON,
                    new CornerRadii(5, 5, 5, 5, false),
                    new Insets(5, 50, 5, 5))));
        } else {
            hbox.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK,
                    new CornerRadii(5, 5, 5, 5, false),
                    new Insets(5, 50, 5, 5))));
        }
        hbox.setPadding(new Insets(5,50,5,5));
        dialog.setPadding(new Insets(15,15,15,0));

    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip(false);
        return db;
    }

    public static DialogBox getDukeErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip(true);
        return db;
    }
}
