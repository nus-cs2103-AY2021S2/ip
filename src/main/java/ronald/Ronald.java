package ronald;

import ronald.storage.Storage;
import ronald.ui.Ui;
import ronald.util.Parser;

public class Ronald {
    private static final String PATHNAME = "./data.txt";

    /**
     * Initialises Duke application with default data file path and populates task list.
     */
    public Ronald() {
        Storage.initialisePath(PATHNAME);
        Storage.initialiseList();
    }

    /**
     * Gets response based on input.
     *
     * @param input user input.
     * @return response that is to be printed onto GUI.
     */
    public String getResponse(String input) {
        Parser.parseAndProcess(input);
        return Ui.getNextResponse();
    }
}

