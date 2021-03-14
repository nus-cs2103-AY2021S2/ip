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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.Collections;

/**
 * Custom control using FXML to represent the speaker's face and a label
 */
public class Dialog extends HBox {
    @FXML
    private Circle displayPic;
    @FXML
    private Label dialog;

    /**
     * Constructor to instantiate a new Dialog object
     * @param text  the text
     * @param image the image
     */
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
        ImagePattern ip = new ImagePattern(image);
        displayPic.setFill(ip);
    }
    /**
     * Constructor to instantiate a new Dialog object
     */
    private void flip() {
        ObservableList<Node> observableList = FXCollections.observableArrayList(this.getChildren());
        setAlignment(Pos.TOP_LEFT);
        Collections.reverse(observableList);
        getChildren().setAll(observableList);
    }

    /**
     * Retrieves response from Duke
     * @param str     the Duke response
     * @param dukeImg the Duke display picture
     * @return        the new Dialog
     */
    public static Dialog getDukeResponse(String str, Image dukeImg) {
        var dialog = new Dialog(str, dukeImg);
        dialog.flip();
        return dialog;
    }

    /**
     * Retrieves input from User
     * @param str     the user input
     * @param userImg the user display picture
     * @return        the new Dialog
     */
    public static Dialog getUserResponse(String str, Image userImg) {
        var dialog = new Dialog(str, userImg);
        return dialog;
    }

    /**
     * Retrieves the Duke dialog
     * @param str   the Duke response
     * @param img   the display picture
     * @return      the new Dialog
     */
    public static Dialog getDukeDialog(String str, Image img) {
        var dialog = new Dialog(str, img);
        dialog.flip();
        return dialog;
    }
}