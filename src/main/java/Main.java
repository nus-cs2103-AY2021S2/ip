import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seashell.Seashell;
import seashell.Ui;

import java.io.IOException;

public class Main extends Application {
    /**
     * Runs the main program
     * @param args
     */
    private Seashell seashell = new Seashell();
    private Image seashellImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSeashell(seashell);
            VBox dialogContainer = new VBox();
            dialogContainer.getChildren().addAll(DialogBox.getSeashellDialog(Ui.showWelcome(), seashellImage));
            stage.setTitle("Seashell, the task manager for you!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
