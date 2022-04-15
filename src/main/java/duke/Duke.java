package duke;

import duke.commands.ErrorCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.commands.Command;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

/**
 * Duke allows the user to maintain a list of tasks, and responses to user commands.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath = "";

    /**
     * Initialises Duke.
     *
     * @param filePath File path to a .txt file containing the list of tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.filePath = filePath;
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            File directory = new File("data");
            directory.mkdir();
            File file = new File(filePath);
            try {
                file.createNewFile();
            } catch (IOException ex){
                new ErrorCommand(tasks, ex.getMessage());
            }
        }
    }

    /**
     * Returns response from Duke when the user keys in his / her input.
     *
     * @param input String input from the user.
     * @return String response from Duke.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(this.tasks);
        Command command = parser.parse(input);
        command = command.process();
        // message is generated before modifying the task list so that we can get
        // the corresponding string for the deleted task when delete is called
        String message = command.toString();
        this.tasks = command.execute();
        this.storage.save(this.tasks);
        return "Duke says:\n" + this.ui.format(message);
    }

    /**
     * Returns a greeting, in String, to the user.
     *
     * @return Greeting, in String.
     */
    public String getGreeting() {
        return this.ui.greet();
    }
}
