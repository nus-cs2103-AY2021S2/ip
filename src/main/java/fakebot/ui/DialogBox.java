package fakebot.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


//Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html

/**
 * Dialog Box for respond and user input
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructors for DialogBox specifying label and image view
     *
     * @param l text to be display
     * @param iv user profile image to be display
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);

        this.setAlignment(Pos.CENTER_RIGHT);

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGREEN,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        this.setBackground(background);

        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);

        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGREY,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        this.setBackground(background);

        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns dialog box for user
     * @param l text of user
     * @param iv imageView of user
     * @return Returns dialog box of the user
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }


    /**
     * Returns dialog box for fakebot
     * @param l text of fakebot
     * @param iv imageView of fakebot
     * @return Returns dialog box of the fakebot
     */
    public static DialogBox getFakebotDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
