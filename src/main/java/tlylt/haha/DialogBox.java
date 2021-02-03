package tlylt.haha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    public DialogBox(Label text, ImageView imageView) {
        this.text = text;
        displayPicture = imageView;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> listOFNodes = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(listOFNodes);
        this.getChildren().setAll(listOFNodes);
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    public static DialogBox getHahaDialog(Label label, ImageView imageView) {
        DialogBox dialogBox = new DialogBox(label, imageView);
        dialogBox.flip();
        return dialogBox;
    }
}
