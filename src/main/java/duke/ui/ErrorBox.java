package duke.ui;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Encapsulates an ErrorBox class that deals with and displays exceptions.
 */
public class ErrorBox {
    /**
     * Displays an Error box with the given title and the alert message.
     *
     * @param message the main message of the Error.
     */
    public static void display(String message) {
        Stage window = new Stage();

        // disable actions in other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("");
        window.setMinWidth(250);

        Label errorContent = new Label();
        errorContent.setText(message);
        errorContent.setWrapText(true);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());
        closeButton.setDefaultButton(true);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(errorContent, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 300);
        window.setScene(scene);
        window.showAndWait();
    }
}
