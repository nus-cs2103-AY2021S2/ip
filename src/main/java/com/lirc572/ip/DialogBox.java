package com.lirc572.ip;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Represent a dialog box in the dialog container.
 */
public class DialogBox extends HBox {

    private final double imageRadius = 20.0;
    private TextFlow textFlow;
    private Circle imageCircle;

    /**
     * Constructs a new DialogBox object.
     * @param labelString text in the dialog box.
     * @param imageSrc path to the image in the dialog.
     */
    public DialogBox(String labelString, String imageSrc) {
        this(new TextFlow(new Text(labelString)), imageSrc);
    }

    /**
     * Constructs a new DialogBox object.
     * @param textFlow text in the dialog box.
     * @param imageSrc path to the image in the dialog.
     */
    public DialogBox(TextFlow textFlow, String imageSrc) {
        this.textFlow = textFlow;
        this.imageCircle = new Circle(this.imageRadius);
        ImagePattern pattern = new ImagePattern(new Image(this.getClass().getResourceAsStream(imageSrc)));
        this.imageCircle.setFill(pattern);
        this.imageCircle.setEffect(new DropShadow(15.0, Color.BLACK));

        this.textFlow.setPadding(new Insets(0.0, 10.0, 0.0, 10.0));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.textFlow, this.imageCircle);
    }

    /**
     * Constructs a new DialogBox object for the user (on the right).
     * @param labelString text in the dialog box.
     * @param imageSrc path to the image in the dialog.
     * @return a new DialogBox object.
     */
    public static DialogBox getUserDialogBox(String labelString, String imageSrc) {
        return new DialogBox(convertStringToTextflow(labelString), imageSrc);
    }

    /**
     * Constructs a new DialogBox object for the user (on the right).
     * @param textFlow text in the dialog box.
     * @param imageSrc path to the image in the dialog.
     * @return a new DialogBox object.
     */
    public static DialogBox getUserDialogBox(TextFlow textFlow, String imageSrc) {
        return new DialogBox(textFlow, imageSrc);
    }

    /**
     * Constructs a new DialogBox object for Elaina (on the left).
     * @param labelString text in the dialog box.
     * @param imageSrc path to the image in the dialog.
     * @return a new DialogBox object.
     */
    public static DialogBox getElainaDialogBox(String labelString, String imageSrc) {
        var dialogBox = new DialogBox(convertStringToTextflow(labelString), imageSrc);
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Constructs a new DialogBox object for Elaina (on the left).
     * @param textFlow text in the dialog box.
     * @param imageSrc path to the image in the dialog.
     * @return a new DialogBox object.
     */
    public static DialogBox getElainaDialogBox(TextFlow textFlow, String imageSrc) {
        var dialogBox = new DialogBox(textFlow, imageSrc);
        dialogBox.flip();
        return dialogBox;
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Converts a String to a TextFlow for DialogBox.
     * @param str String to be converted.
     * @return The converted TextFlow.
     */
    private static TextFlow convertStringToTextflow(String str) {
        TextFlow result = new TextFlow();
        String[] matches = Pattern.compile("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\."
                + "[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)")
                .matcher(str)
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new);
        for (String match : matches) {
            int pos = str.indexOf(match);
            Hyperlink link = new Hyperlink(match);
            link.setOnMouseClicked((event) -> {
                try {
                    Desktop.getDesktop().browse(new URL(match).toURI());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            });
            result.getChildren().addAll(new Text(str.substring(0, pos)), link);
            str = str.substring(pos + match.length());
        }
        result.getChildren().add(new Text(str));
        return result;
    }
}
