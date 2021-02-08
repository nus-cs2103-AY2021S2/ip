package duke;

import duke.controllers.Ui;
import duke.models.Storage;
import duke.views.Greeting;

public class Duke {
    /** Storage object to be passed to AppController for reading / writing to db */
    private final Storage storage;
    private final Ui ui;

    /**
     * Initialises Duke to provide responses to front-end JavaFX
     * @param filePath path to text file which contains Duke's database
     * @param directoryPath path to directory which contains text file of Duke's database
     */
    public Duke(String filePath, String directoryPath) {
        // file path and directory path should never be null
        // this will cause issues with Storage
        assert filePath != null : "Database file path for Duke is null";
        assert directoryPath != null : "Database directory path for Duke is null";
        this.storage = new Storage(filePath, directoryPath);
        this.ui = new Ui(storage);
    }

    /**
     * Returns input to front-end JavaFX to be displayed
     * @param input user input provided by front-end
     * @return output from user input provided from front-end basaed on Duke logic
     */
    public String getResponse(String input) {
        return ui.respondToInput(input);
    }

    /**
     * Returns String greeting from the Greeting class
     * @return greeting for a user
     */
    public String getGreeting() {
        return Greeting.greet();
    }
}
