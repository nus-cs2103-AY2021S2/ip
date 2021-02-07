package yoda;

import yoda.command.Command;
import yoda.parser.Parser;
import yoda.storage.Storage;
import yoda.task.TaskList;
import yoda.ui.Ui;

/**
 * Yoda chatbot to keep track of tasks.
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
        String filePath = home + "/dukeTasks.txt";
        storage = new Storage(filePath);
        this.ui = new Ui();
        this.tasks = storage.load();
    }

    /**
     * Runs the Yoda chatbot.
     */
    public String runYoda() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readInput();
            ui.showLine();
            Command c = Parser.parse(command);
            c.execute(tasks, ui, storage);
            ui.showLine();
            isExit = c.isExit();
        }
    }
}
