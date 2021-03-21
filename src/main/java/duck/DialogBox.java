package duck;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class DialogBox extends HBox {

    private static String indentation = "   ";
    private Label text;
    private ImageView displayPicture;

    /**
     * Create dialog box which include the Avatar and label
     *
     * @param l  the label including dialogue
     * @param iv the avatar
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;
        Label textSpace = new Label(indentation);
        text.setWrapText(true);
        displayPicture.setFitWidth(60.0);
        displayPicture.setFitHeight(60.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, textSpace, displayPicture);

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

    /**
     * create user's dialog box
     *
     * @param l  the label including dialogue
     * @param iv the avatar of user
     * @return dialog box
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        DialogBox userDialog = new DialogBox(l, iv);
        userDialog.setStyle("-fx-padding: 10;"
                + "-fx-border-style: dashed;"
                + "-fx-border-width: 4;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: #87409c;"
                + "-fx-background-color: #FAF0E6;"
                + "-fx-font-size: 14px;"
                + "-fx-font-weight:bold"
        );
        return userDialog;
    }

    /**
     * create Duke's dialog box
     *
     * @param l  the label including dialogue
     * @param iv the avatar of Duke
     * @return dialog box
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv, boolean isErrorReply) {

        if (isErrorReply) {
            l.setStyle("-fx-text-fill: Red;");
        }
        var duckDialog = new DialogBox(l, iv);
        duckDialog.flip();
        duckDialog.setStyle("-fx-padding: 10;"
                + "-fx-border-style: dotted;"
                + "-fx-border-width: 4;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: #4d70a3;"
                + "-fx-background-color: #cadcf6;"
                + "-fx-font-size: 14px;"
        );
        return duckDialog;
    }
}
