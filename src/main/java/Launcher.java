import javafx.application.Application;
import gui.DukeUi;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(DukeUi.class, args);
    }
}
