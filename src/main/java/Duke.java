import duke.Storage;
import duke.Ui;
import duke.Parser;
    
/** Read in user inputs and perform actions accordingly. */
public class Duke {

    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser();
    }

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
