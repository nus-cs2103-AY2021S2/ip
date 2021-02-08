package duke.duke;

import java.io.File;
import java.io.IOException;

import duke.command.Command;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.MainWindow;
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
    //private final static File f = new File("src/main/data/duke.txt");
    private static final File file = new File("duke.txt");

    @Override
    public void init() {

    }

    @Override
    public void start(Stage stage) {
        Duke bot = startMain();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Duke");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(bot);
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
     * @return A Duke object that manages task list operations
     */
    public static Duke startMain() {
        Duke bot = null;
        try {
            if (!(file.createNewFile())) {
                TaskList previous = Storage.runFile(file);
                bot = new Duke(previous);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (bot == null) {
            bot = new Duke();
        }
        return bot;
    }

    /**
     * Runs a user command.
     * @param userCommand A user command.
     * @param bot A duke object.
     * @return Output message of String to be shown to user.
     * @throws IOException If error occurs while writing to file.
     */
    public static String runUserCommand(Command userCommand, Duke bot) throws IOException {
        return userCommand.run(file, bot);
    }
}
