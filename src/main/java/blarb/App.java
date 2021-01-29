package blarb;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Application file for the GUI version.
 */
public class App extends Application {
    /**
     * Initializes the main application.
     *
     * @param stage The entry stage of the application.
     */
    @Override
    public void start(Stage stage) {
        Blarb blarb = new Blarb();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<Gui>getController().setBlarb(blarb);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
