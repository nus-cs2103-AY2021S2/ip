package chat;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class that handles the GUI for Chat using FXML.
 */
public class Main extends Application {

    private Chat chat = new Chat("data/tasks.txt");

    /**
     * Main entry point for JavaFX application.
     * 
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            MainWindow mainWindow = new MainWindow(chat);
            fxmlLoader.setController(mainWindow);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Chat the Cat");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}