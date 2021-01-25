package duke;

import duke.storage.Storage;

import static duke.Ui.*;

public class Duke {
    public static void main(String[] args) {
        try {
            displayWelcome();
            Storage.initialiseList();
            Parser.parseAndProcessInput();
            displayFarewell();
        } catch (Exception e) {
            displayError(e.getMessage());
        }
    }
}

