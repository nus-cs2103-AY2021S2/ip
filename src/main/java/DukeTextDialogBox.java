import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DukeTextDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DukeTextDialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    MainWindow.class.getResource("/view/DukeTextDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.loadProperties(text, image);
    }

    public static DukeTextDialogBox getDialogBox(String text, Image img) {
        return new DukeTextDialogBox(text, img);
    }

    private void loadProperties(String text, Image image) {
        this.setDisplayPicture(image);
        this.setText(text);
    }

    private void setDisplayPicture(Image image) {
        this.displayPicture.setImage(image);
    }

    private void setText(String text) {
        this.dialog.setText(text);
    }
}
