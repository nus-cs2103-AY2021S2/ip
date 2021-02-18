package duke;


import duke.utils.Statistics;
import javafx.application.Application;

/**
 * Encapsulates the main class of the Object.
 */
public class Duke {
    /**
     * Executes the programs.
     *
     * @param args command line arguments key in by user.
     */
    public static void main(String[] args) {
        Statistics.initialize();
        Application.launch(Main.class, args);
    }

}
