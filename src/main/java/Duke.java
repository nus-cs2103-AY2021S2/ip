import javafx.application.Application;

/**
 * Class that can create Duke objects that help the user
 * to maintain a list of tasks.
 */
public class Duke {
    /**
     * Method to run the Duke application.
     */
    public void run() {
        Application.launch(UI.class);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
