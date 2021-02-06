package duke;

import duke.controllers.Ui;
import duke.models.Storage;

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

}
