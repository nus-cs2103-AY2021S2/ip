package duke;

import duke.storage.Storage;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.tasks.TaskList;

import java.io.FileNotFoundException;

/**
 * Duke allows the user to maintain a list of tasks
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath = "";

    public Duke(String filePath) {
        this.ui = new Ui();
        this.ui.greet();
        this.storage = new Storage(filePath);
        this.filePath = filePath;
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Parser parser = new Parser();
        parser = parser.parse(input);
        String message = this.ui.getOutput(this.tasks, parser);
        this.tasks = this.ui.process(this.tasks, parser);
        this.storage.save(this.tasks);
        return "Duke says:\n" + message;
    }

    public String getGreeting() {
        return this.ui.greet();
    }
}
