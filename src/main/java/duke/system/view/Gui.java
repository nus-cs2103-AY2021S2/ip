package duke.system.view;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Gui extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/Window.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
//            scene.getStylesheets().add(getClass().getResource("demo.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<Window>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
