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
        this.tl = new TaskList();
    }

    /**
     * Simulates the running of Duke.
     * A interative task planner.
     *
     */
    public void run() {
        ui.printGreeting();
        Storage.load(tl);
        String input;
        input = ui.readLine();
        while (!input.equals("bye")) {
            try {
                Command c = Parser.parseInput(input);
                c.execute(tl, ui, storage);
            } catch (Exception e) {
                ui.printError(e.toString());
            } finally {
                input = ui.readLine();
            }
        }
        ui.printBye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}

