package jaryl.duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    private Duke duke = new Duke();

    @Override
    public void start(Stage s) {
        try {
            FXMLLoader fxml = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxml.load();
            s.setScene(new Scene(anchorPane));
            fxml.<MainWindow>getController().setDuke(duke);
            s.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}