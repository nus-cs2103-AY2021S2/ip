import duke.Duke;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for duke using Javafx
 * @author WangYihe
 * @author E0424695
 * @version 2.2
 */
public class Main extends Application {

    private Duke duke = new Duke();
    private MainWindow mainWindow = new MainWindow();

    @Override
    public void start(Stage stage) {
        mainWindow.initialize();
        Scene scene = new Scene(mainWindow.mainLayout);
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.getIcons().add(mainWindow.dukeImage);

        mainWindow.setDuke(duke);
        mainWindow.initializeComponentProperties();
        mainWindow.initializeEventListeners();

        mainWindow.printWelcomeMessage(duke);
    }
}
