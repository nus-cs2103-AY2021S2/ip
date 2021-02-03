import duke.util.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Driver extends Application {
    private static final String STAGE_TITLE = "Duke";
    private static final String BYE_STRING = "bye";
    private static final String MAIN_WINDOW_FXML = "/view/MainWindow.fxml";

    /**
     * The main method runs the program.
     *
     * @param args The command line arguments entered into the program.
     */

    public static void main(String[] args) {
        Ui userInterface = new Ui();
        try {
            Duke duke = Duke.start();
            userInterface.displayMessage(userInterface.printGreetings());

            String sentence = userInterface.readCommand();
            while (isNotEqualBye(sentence)) {
                String message = duke.doCommand(sentence);
                userInterface.displayMessage(message);
                sentence = userInterface.readCommand();
            }

        } finally {
            userInterface.displayMessage(userInterface.getBye());
        }
    }

    /**
     * Returns whether the command string equals to bye.
     *
     * @param command The command entered by the client.
     * @return The boolean value.
     */

    private static boolean isNotEqualBye(String command) {
        return !command.equals(BYE_STRING);
    }

    /**
     * Starts the app's GUI by loading the resources from MainWindow.fxml.
     *
     * @param stage The stage of the GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource(MAIN_WINDOW_FXML));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle(STAGE_TITLE);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(Duke.start());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}