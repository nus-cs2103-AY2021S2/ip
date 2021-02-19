package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/** Reads in user inputs and perform actions accordingly. */
public class Duke {
    /** Template for replying user. */
    private Ui ui;
    /** Storage to store and update tasks entered in hard drive. */
    private Storage storage;
    /** Helper to make sense of user inputs. */
    private Parser parser;

    /** Initialises Duke with ui, storage and parser. */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser(this.storage);
    }

    public Ui getUi() {
        return this.ui;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            String reply;
            reply = this.parser.process(input);
            assert reply.length() != 0 : "Bot should always reply to every user input";
            return reply;
        } catch (DukeException err) {
            return err.getMessage();
        }
    }
}
