import duke.Storage;
import duke.Ui;
import duke.Parser;

/** Reads in user inputs and perform actions accordingly. */
public class Duke {
    /** Storage to store and update tasks entered in hard drive. */
    private Storage storage;
    /** Template for replying user. */
    private Ui ui;
    /** Helper to make sense of user inputs. */
    private Parser parser;

    /** Initialises Duke with ui, storage and parser. */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser();
    }

    /** Activates Duke charbot. */
    public void run() {
        this.ui.greet();
        this.parser.chat();
    }

    /**
     * Reads input containing keyword and content of task from user.
     * Add, delete, view, mark as done or exit accordingly.
     * @param args Texts entered by user.
     */ 
    public static void main(String[] args) {
        new Duke().run();
    }
}
