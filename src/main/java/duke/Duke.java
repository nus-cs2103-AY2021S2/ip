package duke;

/**
 * Duke is a chatbot that helps the user to keep track of tasks that needs to be done.
 */

public class Duke {

    /**
     * Construct duke object with parser, storage and ui
     */
    public Duke() {
        Parser parser = new Parser();
        Storage storage = new Storage();
        Ui ui = new Ui();
    }
}
