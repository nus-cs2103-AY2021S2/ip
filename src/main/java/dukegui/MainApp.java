package dukegui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import dukebody.Duke;

public class MainApp extends Application {
    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(
                    "/view/MainWindow.fxml"));

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().syncDuke(duke);
            stage.show();

            // greet user
            fxmlLoader.<MainWindow>getController().dukeOutput("I am DogeDuke. what are you?\n"
                    + "Please type in your username.\n\nTo specify a database to "
                    + "connect to, input /read_from:<path>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
