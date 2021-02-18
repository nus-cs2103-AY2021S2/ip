package bearbear.bearbear;

import java.io.File;
import java.io.IOException;

import bearbear.command.Command;
import bearbear.exceptions.StorageException;
import bearbear.storage.Storage;
import bearbear.tasks.TaskList;
import bearbear.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main entry point to the chat bot application.
 * Initialises application and starts user interaction.
 */

public class Main extends Application {
    private static final File file = new File("src/main/data/BearBear.txt");

    @Override
    public void init() {

    }

    @Override
    public void start(Stage stage) throws StorageException {
        BearBear bot = startMain();
        assert bot != null : "There should be a BearBear object at this point";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("BearBear");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBearBear(bot);
            fxmlLoader.<MainWindow>getController().showWelcomeMessage();
            stage.setMinWidth(400.0);
            stage.setMinHeight(600.0);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes task list from storage.
     * @return A BearBear object that manages task list operations
     */
    public static BearBear startMain() throws StorageException {
        BearBear bot = null;
        TaskList previous = Storage.runFile(file);

        if (previous != null) {
            bot = new BearBear(previous);
        }

        if (bot == null) {
            bot = new BearBear();
        }

        return bot;
    }

    /**
     * Runs a user command.
     * @param userCommand A user command.
     * @param bot A BearBear object.
     * @return Output message of String to be shown to user.
     * @throws IOException If error occurs while writing to file.
     */
    public static String runUserCommand(Command userCommand, BearBear bot) throws IOException {
        assert userCommand != null : "User command should not be empty at this point";
        assert file != null;
        return userCommand.run(file, bot);
    }
}
