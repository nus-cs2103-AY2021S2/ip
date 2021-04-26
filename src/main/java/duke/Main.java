package duke;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Rem rem = new Rem("data/tasks.txt");
    private final String applicationName = "Rem";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(applicationName);
            fxmlLoader.<MainWindow>getController().setMomo(rem);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
