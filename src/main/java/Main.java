import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mike.Mike;

public class Main extends Application {

    private Mike mike = new Mike();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "AnchorPane is Null.";
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Mike the Hawk");
            fxmlLoader.<MainWindow>getController().setMike(mike);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
