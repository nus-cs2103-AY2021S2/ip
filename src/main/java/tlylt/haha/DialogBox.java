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
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;


public class DialogBox extends HBox {
    private static final int DIALOG_BOX_PADDING = 10;
    private static final int TEXT_PADDING = 5;
    private static final double PICTURE_SIZE = 90.0;
    private static final double SPACING = 20.0;
    private static final Color TEXT_BACKGROUND_COLOR = Color.rgb(224, 234, 245);
    private static final Color TEXT_SHADOW_COLOR = Color.rgb(200, 220, 230);
    private static final DropShadow TEXT_SHADOW = new DropShadow(BlurType.GAUSSIAN, TEXT_SHADOW_COLOR,
            2, 10, 3, 3);
    private final Label text;
    private final ImageView displayPicture;

    /**
     * Generates a DialogBox that displays the interactions between user and the program.
     */
    //@@author Jeffry Lum-reused
    //Reused from https://se-education.org/guides/tutorials/javaFx.html with minor modifications
    public DialogBox(Label text, ImageView imageView) {
        super(SPACING);
        this.text = text;
        displayPicture = imageView;

        configureText();
        configurePicture();
        configureDialogBox();
    }


    private void configureText() {
        text.setWrapText(true);
        text.setMinHeight(Region.USE_PREF_SIZE);
        text.setAlignment(Pos.CENTER);
        text.setPadding(new Insets(TEXT_PADDING, TEXT_PADDING, TEXT_PADDING, TEXT_PADDING));
        text.setBackground(new Background(new BackgroundFill(TEXT_BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        text.setStyle("-fx-text-fill:#31456A;-fx-font-size: 11px;-fx-font-weight:bold;");
        text.setEffect(TEXT_SHADOW);
    }

    private void configurePicture() {
        displayPicture.setFitWidth(PICTURE_SIZE);
        displayPicture.setFitHeight(PICTURE_SIZE);
    }

    private void configureDialogBox() {
        this.setPadding(new Insets(DIALOG_BOX_PADDING, DIALOG_BOX_PADDING, DIALOG_BOX_PADDING, DIALOG_BOX_PADDING));
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
        return new DialogBox(label, imageView).flip();
    }
}
