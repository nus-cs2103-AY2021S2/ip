import duke.Duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 * Main class is the main class. It implements a simple task manager that has a variety of
 * functions such as listing, adding Todos, Events, Deadlines, deletion and completion.
 *
 * @author : IanCKW
 */
public class Main extends Application {

    private Duke duke = new Duke("data/duke.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main Method
     **/

    public static void main(String[] args) {
        //Duke duke = new Duke("data/duke.txt");
        //duke.start();
    }

}
