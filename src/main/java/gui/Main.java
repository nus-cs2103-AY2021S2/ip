package duke.gui;
import javafx.application.Application;
import javafx.fml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scence.layout.AnchorPane;
import java.io.IOException;

public class Main extends Application{
    private Duke duke = new Duke("data/duke.txt");
    @Override
    public void start(Stage stage){
        MainWindow mainWindow = new MainWindow(duke);
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.show();
    }
}
