package duke;

import duke.storage.Storage;

public class Duke {
    private static final String PATHNAME = "./data.txt";

    public Duke() {
        Storage.initialisePath(PATHNAME);
        Storage.initialiseList();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        Parser.parseAndProcess(input);
        return Ui.getNextResponse();
    }
}

