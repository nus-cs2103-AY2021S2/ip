package duke;

import duke.storage.Storage;

import static duke.Ui.*;

public class Duke {
    private static final String PATHNAME = "./src/main/java/duke/storage/data.txt";

    public static void main(String[] args) {
        try {
            displayWelcome();
            Storage.initialisePath(PATHNAME);
            Storage.initialiseList();
            Parser.parseAndProcessInput();
            displayFarewell();
        } catch (Exception e) {
            displayError(e.getMessage());
        }
    }
}

