package com.lirc572.ip;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Represent a dialog box in the dialog container.
 */
public class DialogBox extends HBox {

    private final double imageRadius = 20.0;
    private Label textLabel;
    private Circle imageCircle;

    /**
     * Constructs a new DialogBox object.
     * @param labelString text in the dialog box.
     * @param imageSrc path to the image in the dialog.
     */
    public DialogBox(String labelString, String imageSrc) {
        this.textLabel = new Label(labelString);
        this.imageCircle = new Circle(this.imageRadius);
        ImagePattern pattern = new ImagePattern(new Image(this.getClass().getResourceAsStream(imageSrc)));
        this.imageCircle.setFill(pattern);
        this.imageCircle.setEffect(new DropShadow(15.0, Color.BLACK));

        this.textLabel.setWrapText(true);
        this.textLabel.setPadding(new Insets(0.0, 10.0, 0.0, 10.0));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.textLabel, this.imageCircle);
    }

    /**
     * Constructs a new DialogBox object for the user (on the right).
     * @param labelString text in the dialog box.
     * @param imageSrc path to the image in the dialog.
     * @return a new DialogBox object.
     */
    public static DialogBox getUserDialogBox(String labelString, String imageSrc) {
        return new DialogBox(labelString, imageSrc);
    }

    /**
     * Constructs a new DialogBox object for duke (on the left).
     * @param labelString text in the dialog box.
     * @param imageSrc path to the image in the dialog.
     * @return a new DialogBox object.
     */
    public static DialogBox getDukeDialogBox(String labelString, String imageSrc) {
        var dialogBox = new DialogBox(labelString, imageSrc);
        dialogBox.flip();
        return dialogBox;
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
