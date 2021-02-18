import java.io.IOException;
import java.util.Collections;

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

public class UserDialogBox extends DialogBox{
    @FXML
    private Label nameLabel, dialog;
    @FXML
    private ImageView displayPicture;

    private String resourceName = "/view/UserDialogBox.fxml";

    public UserDialogBox(String name, String text, Image img, String resourceName) {
        super(name, text, img, resourceName);
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
//            fxmlLoader.setController(this);
//            fxmlLoader.setRoot(this);
//            fxmlLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        nameLabel.setText(name);
//        dialog.setText(text);
//        displayPicture.setImage(img);
    }

    public static UserDialogBox getDialog(String name, String text, Image img, String resourceName) {
        return new UserDialogBox(name, text, img, resourceName);
    }
}
