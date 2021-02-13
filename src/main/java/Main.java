import java.io.IOException;

import chip.Chip;
import chip.exceptions.ChipException;
import chip.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for duke.Duke using FXML.
 */
public class Main extends Application {
    private Chip chip;

    /**
     * Constructor for Main class
     */
    public Main() {
        try {
            chip = new Chip("data/tasks.txt");
        } catch (ChipException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Chip the Squirrel");
            fxmlLoader.<MainWindow>getController().setChip(chip);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
