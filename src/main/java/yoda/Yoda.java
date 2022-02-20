package yoda;

import yoda.command.Command;
import yoda.parser.Parser;
import yoda.storage.Storage;
import yoda.task.TaskList;
import yoda.ui.Ui;

/**
 * Yoda class which contains the main logic of the Yoda chatbot.
 */
public class Yoda {
    /** Specialised list to handle task-related instructions */
    private TaskList tasks;
    /** Storage to handle retrieval and storage of tasklist */
    private Storage storage;
    /** Ui to handle interactions with the user */
    private Ui ui;

    /**
     * Creates a Yoda chatbot object.
     */
    public Yoda() {
        String home = System.getProperty("user.home");
        String filePath = home + "/yodaTasks.txt";
        storage = new Storage(filePath);
        this.ui = new Ui();
        this.tasks = storage.load();
    }

    /**
     * Greets user.
     * @return Message containing greetings.
     */
    public String greetUser() {
        return ui.greet();
    }

    /**
     * Runs the Yoda chatbot by making it parse user input.
     */
    public String runYoda(String input) {
        Command c = Parser.parse(input);
        return c.execute(tasks, ui, storage);
    }
}
