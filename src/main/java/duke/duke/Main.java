package duke.duke;

import java.io.File;
import java.io.IOException;

import duke.command.Command;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.MainWindow;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main entry point to the chat bot application.
 * Initialises application and starts user interaction.
 */

public class Main extends Application {
    //private final static File f = new File("src/main/data/duke.txt");
    private static final File file = new File("duke.txt");
    private MainWindow mainWindow;

    @Override
    public void init() {
        mainWindow = new MainWindow();
    }

    @Override
    public void start(Stage stage) {
        Duke bot = startMain();
        mainWindow.initialise(bot);
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.setScene(mainWindow.getScene());
        stage.show();
    }

    /**
     * Initializes task list from storage and prints welcome greeting.
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

        Ui.showWelcomeMessage(bot);
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
