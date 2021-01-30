package duke;

import duke.storage.Storage;

public class Duke {
    private static final String PATHNAME = "./data.txt";

    /**
     * Runs Duke program.
     *
     * @param args arguments passed into the program
     */
    public static void main(String[] args) {
        try {
            Ui.displayWelcome();
            Storage.initialisePath(PATHNAME);
            Storage.initialiseList();
            Parser.parseAndProcessInput();
            Ui.displayFarewell();
        } catch (Exception e) {
            Ui.displayError(e.getMessage());
        }
    }
}

