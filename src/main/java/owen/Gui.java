package owen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import owen.controller.MainWindow;

public class Gui extends Application {
    private Chatbot bot;

    @Override
    public void start(Stage stage) {
        this.bot = new Owen();

        MainWindow mainWindow = new MainWindow(this.bot);
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
