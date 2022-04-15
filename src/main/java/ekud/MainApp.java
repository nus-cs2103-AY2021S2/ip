package ekud;

import java.io.File;
import java.net.URISyntaxException;

import ekud.ui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The GUI frontend of Ekud.
 */
public class MainApp extends Application {

    private static final String SAVE_FILE_REL_PATH = "data/tasks.txt";

    @Override
    public void start(Stage primaryStage) {
        MainWindow mainWindow = new MainWindow();

        Ekud ekud;
        // find the correct directory to save the stored tasks
        try {
            File jarFile = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            String savePath = jarFile.getParentFile().getPath() + "/" + SAVE_FILE_REL_PATH;
            ekud = new Ekud(savePath);
        } catch (URISyntaxException e) {
            ekud = new Ekud(SAVE_FILE_REL_PATH);
        }

        mainWindow.setEkud(ekud);

        // basic setup for the stage
        primaryStage.setTitle("Ekud: Your Personal Assistant");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(mainWindow));
        primaryStage.show();
    }
}
