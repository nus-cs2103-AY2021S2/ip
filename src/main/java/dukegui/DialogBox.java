package dukegui;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    @FXML
    private Label content;
    @FXML
    private ImageView display;

    public DialogBox(String text, Image image, boolean reverse) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(
                    "/view/DialogBox.fxml"));

            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        content.setText(text);
        display.setImage(image);

        if (reverse) {
            this.setAlignment(Pos.TOP_LEFT);
            ObservableList<Node> tmp = FXCollections.observableArrayList(
                    this.getChildren());

            FXCollections.reverse(tmp);
            this.getChildren().setAll(tmp);
        }
    }

    // default constructor in user perspective
    public DialogBox(String text, Image image) {
        this(text, image, false);
    }

    // mutators
    public void appendText (String text) {
        content.setText(content.getText() + "\n" + text);
    }
}
