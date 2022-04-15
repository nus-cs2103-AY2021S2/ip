package checklst.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DialogBox extends HBox {

    protected Label name;
    protected Label text;
    protected ImageView displayPicture;

    protected DialogBox(String name, Label l, ImageView iv) {
        this.text = l;
        this.text.setFont(new Font("Arial", 14));
        this.displayPicture = iv;
        this.name = new Label(name);

        this.text.setWrapText(true);
        this.text.setPadding(new Insets(10));

        this.displayPicture.setFitWidth(50.0);
        this.displayPicture.setFitHeight(50.0);
        DialogBox.setMargin(this.displayPicture, new Insets(21, 5, 3, 5));

        this.name.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        this.name.setPadding(new Insets(2));

        VBox output = new VBox(this.name, this.text);

        this.setPadding(new Insets(5));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(output, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    protected void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

}
