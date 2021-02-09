package tlylt.haha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;


public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Generates a DialogBox that displays the interactions between user and the program.
     */
    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    public DialogBox(Label text, ImageView imageView) {
        super(20);
        this.text = text;
        this.setPadding(new Insets(10, 10, 10, 10));
        displayPicture = imageView;

        text.setWrapText(true);
        text.setAlignment(Pos.CENTER);
        text.setBackground(new Background(new BackgroundFill(
                Color.rgb(224, 234, 245),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        text.setStyle("-fx-text-fill:#31456A;-fx-font-size: 13px;-fx-font-weight:bold;");
        text.setEffect(new DropShadow(
                BlurType.GAUSSIAN,
                Color.rgb(199, 212, 228),
                3, 5, 0, 0));
        displayPicture.setFitWidth(90.0);
        displayPicture.setFitHeight(90.0);

        this.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().addAll(text, displayPicture);

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    private DialogBox flip() {
        DialogBox reversedDialogBox = new DialogBox(this.text, this.displayPicture);
        reversedDialogBox.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> listOfNodes = FXCollections.observableArrayList(reversedDialogBox.getChildren());
        FXCollections.reverse(listOfNodes);
        reversedDialogBox.getChildren().setAll(listOfNodes);
        return reversedDialogBox;
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    public static DialogBox getUserDialog(Label label, ImageView imageView) {
        return new DialogBox(label, imageView);
    }

    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    public static DialogBox getHahaDialog(Label label, ImageView imageView) {
        DialogBox dialogBox = new DialogBox(label, imageView).flip();
        return dialogBox;
    }
}
