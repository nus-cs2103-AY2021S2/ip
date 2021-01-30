package ekud;

import ekud.ui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    private final Ekud ekud = new Ekud("data/tasks.txt");

    @Override
    public void start(Stage primaryStage) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setEkud(ekud);
        primaryStage.setScene(new Scene(mainWindow));
        primaryStage.show();
    }
}
