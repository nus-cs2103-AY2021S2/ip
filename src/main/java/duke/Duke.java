package duke;

import duke.storage.Storage;

public class Duke {
    private static final String PATHNAME = "./data.txt";

    /**
     * Initialises Duke application with default data file path and populates task list.
     */
    public Duke() {
        Storage.initialisePath(PATHNAME);
        Storage.initialiseList();
    }

    /**
     * Gets response based on input.
     *
     * @param input user input
     * @return response that is to be printed onto GUI
     */
    String getResponse(String input) {
        Parser.parseAndProcess(input);
        return Ui.getNextResponse();
    }
}

