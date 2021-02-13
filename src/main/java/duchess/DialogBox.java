package duchess;

import java.io.IOException;

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


public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /** Creating a custom control using FXML.
     * DialogBox handles dialogues between the user and Duchess by presenting the
     * dialogs and display pictures
     * */
    private DialogBox(String text, Image img) {
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
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String msg, Image picture) {
        var userDialog = new DialogBox(msg, picture);
        userDialog.dialog.setStyle("-fx-background-color: rgb(247, 247, 134); " + "-fx-background-radius: 15;"
                + "-fx-padding: 7.5;" + "-fx-border-color: rgb(237, 237, 119);" + "-fx-border-radius: 15;");
        return userDialog;
    }

    public static DialogBox getDuchessDialog(String msg, Image picture) {
        var duchessDialog = new DialogBox(msg, picture);
        duchessDialog.dialog.setStyle("-fx-background-color: rgb(247, 213, 235); " + "-fx-background-radius: 15;"
               + "-fx-padding: 7.5;" + "-fx-border-color: rgb(235, 188, 218);" + "-fx-border-radius: 15;");
        duchessDialog.flip();
        return duchessDialog;
    }
}
