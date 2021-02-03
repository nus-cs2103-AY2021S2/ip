package duke;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tl;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tl = this.storage.load();
    }

    public static String greeting() {
        return Ui.printGreeting();
    }

    public static void main(String[] args) {
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseInput(input);
            return c.execute(tl, ui, storage);
        } catch (Exception e) {
            return ui.printError(e.toString());
        }
    }
}

