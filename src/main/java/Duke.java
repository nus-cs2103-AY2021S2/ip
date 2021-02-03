import java.io.FileNotFoundException;

/**
 * A personal task managing chatbot project.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/duke.txt");
        try {
            this.tasks = new TaskList(this.storage.readTasksFromFile());
        } catch (FileNotFoundException fileException) {
            this.tasks = new TaskList();
        }
    }

    /**
     * @param input String representing input entered by user.
     * @return String representing Gets the response from Duke to be shown to the user.
     */
    public String getResponse(String input) {
        return Parser.parseInput(input, this.tasks, this.storage);
    }
}
