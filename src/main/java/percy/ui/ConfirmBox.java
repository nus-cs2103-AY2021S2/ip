package percy.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @@author zhixianggg-reused.
 * Reused from thenewboston's YouTube JavaFX GUI Design Tutorial.
 * JavaFX Java GUI Tutorial - 7 - Closing the Program Properly.
 * https://www.youtube.com/watch?v=ZuHcl5MmRck&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=7.
 *
 */
public class ConfirmBox {

    private static boolean answer;

    /**
     * Displays the Confirm Exit Box.
     * @param title title text to be shown.
     * @param message message text to be output.
     * @return answer if user wants to exit or not.
     */
    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
