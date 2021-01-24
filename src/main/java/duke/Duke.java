package duke;

import duke.data.Data;

import static duke.Ui.*;

public class Duke {
    public static void main(String[] args) {
        try {
            displayWelcome();
            Data.initialiseList();
            Parser.parseAndProcessInput();
            displayFarewell();
        } catch (Exception e) {
            displayError(e.getMessage());
        }
    }
}

