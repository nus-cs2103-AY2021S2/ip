package jaryl.duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.util.Collections;

public class Dialog extends HBox {
    @FXML
    private ImageView displayPic;
    @FXML
    private Label dialog;

    private Dialog(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Dialog.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPic.setImage(image);
    }

    private void flip() {
        ObservableList<Node> observableList = FXCollections.observableArrayList(this.getChildren());
        setAlignment(Pos.TOP_LEFT);
        Collections.reverse(observableList);
        getChildren().setAll(observableList);
    }

    public static Dialog getDukeResponse(String str, Image dukeImg) {
        var dialog = new Dialog(str, dukeImg);
        dialog.flip();
        return dialog;
    }

    public static Dialog getUserResponse(String str, Image userImg) {
        var dialog = new Dialog(str, userImg);
        return dialog;
    }
}