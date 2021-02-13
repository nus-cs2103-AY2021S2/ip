package fakebot.ui;

import java.io.IOException;
import java.util.Collections;

import fakebot.MainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Dialog Box Style Type
     */
    private enum Style {
        USER, BOT, ALERT
    }

    /**
     * Constructors for DialogBox specifying label and image view
     *
     * @param text  the text to be display
     * @param img   the user profile image to be display
     * @param style Dialog Box Style
     */
    private DialogBox(String text, Image img, Style style) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Setup Image and text
        dialog.setText(text);
        displayPicture.setImage(img);
        Circle clip = new Circle(30, 30, 30);
        displayPicture.setClip(clip);

        styleBox(style);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Styles the dialog box according to style
     *
     * @param style the style type
     */
    private void styleBox(Style style) {
        CornerRadii rad = new CornerRadii(10);
        Color color = Color.BLACK;
        switch (style) {
        case USER:
            color = Color.rgb(0, 150, 135);
            break;
        case BOT:
            color = Color.rgb(53, 60, 67);
            flip();
            break;
        case ALERT:
            color = Color.rgb(159, 2, 2);
            flip();
            break;
        default:
            assert false : "All Style type should be handle in switch";
            break;
        }

        BackgroundFill backgroundFill = new BackgroundFill(color, rad, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        dialog.setBackground(background);
    }

    /**
     * Returns dialog box for user
     *
     * @param text the text of user
     * @param img  the image of user
     * @return Returns dialog box of the user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, Style.USER);
    }


    /**
     * Returns dialog box for fakebot
     *
     * @param text the text of fakebot
     * @param img  the image of fakebot
     * @return Returns dialog box of the fakebot
     */
    public static DialogBox getFakebotDialog(String text, Image img) {
        return new DialogBox(text, img, Style.BOT);
    }

    /**
     * Returns alert dialog box for fakebot
     *
     * @param text the text of fakebot
     * @param img  the image of fakebot
     * @return Returns dialog box of the fakebot
     */
    public static DialogBox getAlertDialog(String text, Image img) {
        return new DialogBox(text, img, Style.ALERT);
    }
}
