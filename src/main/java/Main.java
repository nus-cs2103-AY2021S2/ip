import duke.Controller;
import duke.ui.Message;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Controller duke = new Controller();
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            //ap.getChildren().add(new Label(Message.getStartMsg()));
            Scene scene = new Scene(ap);
            this.stage = stage;
            stage.setOnCloseRequest(e -> handleExit());
            stage.setTitle("Duke");
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handleExit() {
        stage.close();
    }

    private void handleWelcome() {
        Label label = new Label(Message.getStartMsg());
    }
}